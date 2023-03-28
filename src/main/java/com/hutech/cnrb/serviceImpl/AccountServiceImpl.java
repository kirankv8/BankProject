package com.hutech.cnrb.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hutech.cnrb.dto.AccountDto;
import com.hutech.cnrb.email.EmailSender;
import com.hutech.cnrb.entity.Account;
import com.hutech.cnrb.entity.Bank;
import com.hutech.cnrb.entity.Transactions;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.exceptionhandler.UserNameAlreadyExistException;
import com.hutech.cnrb.repository.AccountRepository;
import com.hutech.cnrb.repository.BankRepository;
import com.hutech.cnrb.repository.TransactionRepository;
import com.hutech.cnrb.response.AccountResponse;
import com.hutech.cnrb.response.AccountsResponse;
import com.hutech.cnrb.service.AccountService;

import jakarta.mail.MessagingException;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private EmailSender sender;

	@Override
	public AccountResponse saveAccount(AccountDto accountDto, String bankId)
			throws ResourceNotFoundException, UserNameAlreadyExistException, MessagingException {

		if (bankRepository.findByBankId(bankId) == null) {
			throw new ResourceNotFoundException("given branchId is not present in the Db", 404);
		}
		if (accountRepository.findByAdharcardNumber(accountDto.getAdharcardNumber()) != null) {
			throw new UserNameAlreadyExistException("given user adharnumber  name already exts's in our DB", 409);
		}
		Account account = mapper.map(accountDto, Account.class);
		Bank bank = bankRepository.findByBankId(bankId);
		bank.setBankAmount(bank.getBankAmount() + accountDto.getAccountBalance());
		bankRepository.save(bank);
		account.setBankId(bank.getBankId());
		accountRepository.save(account);
		Transactions transactions = new Transactions();
		transactions.setAccountNumber(account.getAccountNumber());
		transactions.setDescription("deposite");
		transactions.setDate(LocalDate.now());
		transactions.setBalance(accountDto.getAccountBalance());
		transactions.setBankId(bankId);
		transactions.setSource("bank_account");
		transactions.setTransactionAmount(account.getAccountBalance());
		transactionRepository.save(transactions);
		sender.sendEmail(accountDto.getEmail(), "WELCOME TO THE CANON-BANK",
				"DEAR<b> " + account.getName() + "<br> </b><b> THANK YOU</b> FOR THE OPENING ACCOUNT IN OUR BANK<br>"
						+ "your Account Number is = <b>" + account.getAccountNumber() + "</b><br> accountBalance =<b>"
						+ account.getAccountBalance() + "</b><br> transaction through = "
						+ transactions.getDescription() + "<br> THANK-YOU CHOOSING AS HAVE A GOOD DAY");
		AccountResponse response = mapper.map(account, AccountResponse.class);
		return response;
	}

	@Override
	public List<AccountsResponse> getAllCustomers(String bankId) throws ResourceNotFoundException {
		List<Account> listOFaccounts = accountRepository.findByBankId(bankId);
		List<AccountsResponse> list = new ArrayList<>();
		for (Account account : listOFaccounts) {
			List<Transactions> deposites = transactionRepository.findByDescriptionAndAccountNumber("deposite",
					account.getAccountNumber());
			List<Transactions> withdraws = transactionRepository.findByDescriptionAndAccountNumber("withdraw",
					account.getAccountNumber());
			Transactions lastTransaction = transactionRepository
					.findFirstByAccountNumberOrderByDateDesc(account.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("given Aaccount balance is null", 404));
			double totalDeposites = deposites.stream().mapToDouble(Transactions::getTransactionAmount).sum();
			double totalWithdraws = withdraws.stream().mapToDouble(Transactions::getTransactionAmount).sum();
			AccountsResponse response = mapper.map(account, AccountsResponse.class);
			response.setCustomerName(account.getName());
			response.setRemaingBalance(account.getAccountBalance());
			response.setTotalExpenses(totalWithdraws);
			response.setTotalEarnings(totalDeposites);
			response.setLastTransaction(lastTransaction.getDate());
			list.add(response);
		}
		return list;
	}

	@Override
	public String getAccountBalance(String bankId, String accId) throws ResourceNotFoundException {
		if (accountRepository.findByBankId(bankId) == null) {
			throw new ResourceNotFoundException("Invalid Bank Id", 404);
		}
		if (accountRepository.findByAccountNumber(accId) == null) {
			throw new ResourceNotFoundException("Invalid Bank Id", 404);
		}
		Account account = accountRepository.findByAccountNumber(accId);
		return "Account Balance =" + account.getAccountBalance();
	}
}