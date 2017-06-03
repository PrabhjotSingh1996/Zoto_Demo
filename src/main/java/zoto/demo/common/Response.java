package zoto.demo.common;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import zoto.demo.model.TransactionHistory;

@JsonInclude(Include.NON_NULL)
public class Response {

	private String accessToken;
	private Error error;
	private Map<String,String> endPoints;
	private String message;
	private List<TransactionHistory> transactionHistory;

	public List<TransactionHistory> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<TransactionHistory> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public Map<String,String> getEndPoints() {
		return endPoints;
	}

	public void setEndPoints(Map<String,String> endPoints) {
		this.endPoints = endPoints;
	}
	
}
