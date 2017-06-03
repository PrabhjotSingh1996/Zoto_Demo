package zoto.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long pKey;
	@Column(nullable=false)
	private Long payer;
	@Column(nullable=false)
	private Long payee;
	@Column(nullable=false)
	private double amount;
	@Column(nullable=false)
	private Timestamp transactionDate;
	
	public TransactionHistory() {
		super();
	}
	public TransactionHistory(Long payer, Long payee, double amount, Timestamp transactionDate) {
		super();
		this.payer = payer;
		this.payee = payee;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}
	public Long getPayer() {
		return payer;
	}
	public void setPayer(Long payer) {
		this.payer = payer;
	}
	public Long getPayee() {
		return payee;
	}
	public void setPayee(Long payee) {
		this.payee = payee;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
