package com.bank.seoui;

public class Accont {

		private String bankAccountNum; // 계좌 번호
		private String name; // 계좌 사용자 이름
		private int inputMoney; // 입금 가격

		public Accont() {
		}

		public Accont(String name, String bankAccountNum, int inputMoney) {
			this.name = name;
			this.bankAccountNum = bankAccountNum;
			this.inputMoney = inputMoney;
		}

		void setinputMoney(int inputMoney) {
			this.inputMoney = inputMoney;
		}

		int getinputMoney() {
			return this.inputMoney;
		}

		void setname(String name) {
			this.name = name;
		}

		String getname() {
			return this.name;
		}

		void setbankAccountNum(String bankAccountNum) {
			this.bankAccountNum = bankAccountNum;
		}

		String getbankAccountNum() {
			return this.bankAccountNum;
		}
}
