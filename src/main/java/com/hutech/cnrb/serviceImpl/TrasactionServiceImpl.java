package com.hutech.cnrb.serviceImpl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hutech.cnrb.dto.TransactionDto;
import com.hutech.cnrb.entity.Account;
import com.hutech.cnrb.entity.Bank;
import com.hutech.cnrb.entity.Transactions;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.repository.AccountRepository;
import com.hutech.cnrb.repository.BankRepository;
import com.hutech.cnrb.repository.TransactionRepository;
import com.hutech.cnrb.response.TransactionAverage;
import com.hutech.cnrb.response.TransactionResponse;
import com.hutech.cnrb.service.TransationService;

@Service
public class TrasactionServiceImpl implements TransationService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public TransactionResponse createtTransactions(TransactionDto trDto, String accountNumber)
			throws ResourceNotFoundException {
		if (accountRepository.findByAccountNumber(accountNumber) == null) {
			throw new ResourceNotFoundException("given account number is invalid", 404);
		}
		Transactions transaction = mapper.map(trDto, Transactions.class);
		Account account = accountRepository.findByAccountNumber(accountNumber);
		Bank bank = bankRepository.findByBankId(account.getBankId());

		if (transaction.getDescription().equalsIgnoreCase("deposite")) {
			transaction.setAccountNumber(accountNumber);
			transaction.setDate(LocalDate.now());
			transaction.setBankId(account.getBankId());
			transaction.setBalance(account.getAccountBalance() + trDto.getTransactionAmount());
			transactionRepository.save(transaction);
			account.setAccountBalance(account.getAccountBalance() + trDto.getTransactionAmount());
			accountRepository.save(account);
			bank.setBankAmount(bank.getBankAmount() + trDto.getTransactionAmount());
			bankRepository.save(bank);
		}
		if (transaction.getDescription().equalsIgnoreCase("withdraw")) {
			if (account.getAccountBalance() < trDto.getTransactionAmount()) {
				throw new ResourceNotFoundException("Your withDraw amount is less then currect account balance", 409);
			}
			transaction.setAccountNumber(accountNumber);
			transaction.setDate(LocalDate.now());
			transaction.setBankId(account.getBankId());
			transaction.setBalance(account.getAccountBalance() - trDto.getTransactionAmount());
			transactionRepository.save(transaction);
			account.setAccountBalance(account.getAccountBalance() - trDto.getTransactionAmount());
			accountRepository.save(account);
			bank.setBankAmount(bank.getBankAmount() - trDto.getTransactionAmount());
			bankRepository.save(bank);
		}
		return this.mapper.map(transaction, TransactionResponse.class);
	}

	@Override
	public TransactionAverage getAverageTransaction(String bankId, String accountId, Integer month)
			throws ResourceNotFoundException {
		if (bankRepository.findByBankId(bankId) == null) {
			throw new ResourceNotFoundException("given bankID is invalid", 404);
		}
		if (accountRepository.findByAccountNumber(accountId) == null) {
			throw new ResourceNotFoundException("given account number is invalid", 404);
		}
		LocalDate today = LocalDate.now();
		YearMonth yearMonth = YearMonth.of(today.getYear(), month);
		LocalDate startDate = yearMonth.atDay(1);
		LocalDate endDate = yearMonth.atEndOfMonth();
		LocalDate tomorrow = today.plusDays(1);

		if (!startDate.isAfter(tomorrow)) {

			startDate = tomorrow.withDayOfMonth(1);
		}

		List<Transactions> transactions = transactionRepository.findByDateAndAccountNumber(startDate, endDate,
				accountId);
		double withdrawAmount = 0;
		int withdrawCount = 0;
		double depositeAmount = 0;
		int depositeCount = 0;

		for (Transactions transa : transactions) {

			if (transa.getDate().isBefore(today)) {
				if (transa.getDescription().equalsIgnoreCase("deposite")) {
					depositeAmount += transa.getTransactionAmount();
					depositeCount++;
				} else if (transa.getDescription().equalsIgnoreCase("withdraw")) {
					withdrawAmount += transa.getTransactionAmount();
					withdrawCount++;
				}
			}
		}
		double avgDeposit = depositeCount > 0 ? depositeAmount / depositeCount : 0;
		double avgWithdrawal = withdrawCount > 0 ? withdrawAmount / withdrawCount : 0;
		TransactionAverage average = new TransactionAverage();
		average.setAverageDeposits(avgDeposit);
		average.setAverageWithdraws(avgWithdrawal);
		return average;
	}
}
