package com.bank.seoui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class BankManager {
	private static ArrayList<Accont> Accont = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);

	public void Menu() { // 메뉴 선택

		String inputString = null;
		boolean falg = true; // 반복 동작을 위한 falg값 입력

		while (falg) {
			System.out.println(" ");
			System.out.println("==================== 한국은행에 오신것을 환영합니다 ==================== ");
			System.out.println(" 1.계좌생성 | 2.계좌조회 | 3.예금 | 4. 출금 | 5. 계좌 삭제 | 6. 프로그램 종료 ");
			inputString = sc.nextLine();
			switch (inputString) {
			case "1": // 계좌 생성
				CreatAccont();
				break;
			case "2": // 계좌 조회
				ListAccont();
				break;
			case "3": // 예금
				Deposit();
				break;
			case "4": // 출금
				Withdrawal();
				break;
			case "5": // 계좌 삭제
				Remove();
				break;
			case "6":
				System.out.println("이용해 주셔서 감사합니다");
				falg = false;
				break;
			}
		}
	}

	static String InputAccont() { // 계좌 입력 받는 곳
		System.out.print("계좌번호 입력>");
		String sAccont = sc.next();
		return sAccont;
	}

	static void Remove() { // 계좌 입력 받고 그 계좌관련 정보 삭제
		// Accont.remove(index)
		int iAccont = 0;

		iAccont = AccontIndex(InputAccont());
		if (iAccont != (-1)) {
			Accont.remove(iAccont);
		} else
			System.out.println("없는 계좌입니다.");
	}

	static int AccontIndex(String sAccont) { // 계좌번호 입력 받고 그 계좌가 존재하는 인덱스 번호 추출
		int index = 0;

		Iterator<Accont> itr = Accont.iterator();
		Accont AccontInfo;

		while (itr.hasNext()) {
			AccontInfo = itr.next();// iterator를 활용해 모든 값 가져오기
			if (AccontInfo.getbankAccountNum().equals(sAccont)) {
				index = Accont.indexOf(AccontInfo);
				break;
			}
		}

		return index;
	}

	static void CreatAccont() { // 계좌 생성
		int deposit = 0;
		boolean bchk = false;
		System.out.println(" 한국 은행을 찾아 주셔서 감사합니다 .");
		System.out.println(" 계좌 생성을 선택하셨습니다 ------");
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("계좌번호 : ");
		String Accontnum = sc.next();
		while (!bchk) {
			System.out.print("입금액 : ");
			deposit = sc.nextInt();
			sc.nextLine();
			if (!DepositLimitChk(deposit))
				System.out.println("10원 이상 1,000,000원 미만으로 입금하십시오");
			else
				bchk = true;
		}
		Accont.add(new Accont(name, Accontnum, deposit));
		System.out.println("계좌 생성이 완료되었습니다. 감사합니다.");
	}

	static void ListAccont() { // 계좌 조회
		String str = "";
		System.out.println(" 계좌 조회를 선택하셨습니다 ------");
		System.out.println(" 이름\t계좌번호\t총액");
		if (Accont.size() == 0)
			System.out.println("입력된 정보가 없습니다.");
		else {
			for (int i = 0; i < Accont.size(); i++) {
				str = Accont.get(i).getname() + "\t" + Accont.get(i).getbankAccountNum() + "\t\t"
						+ Accont.get(i).getinputMoney();
				System.out.println(str);
			}
		}
	}

	static void Deposit() { // 계좌 입금
		int ideposit = 0;
		int index = 0;

		index = AccontIndex(InputAccont());

		if (index != (-1)) {
			System.out.println(" 입금을 선택하셨습니다 ---------");
			System.out.println(" 예금액 : ");
			ideposit = sc.nextInt();
			sc.nextLine();
			Accont.get(index).setinputMoney(Accont.get(index).getinputMoney() + ideposit);
		} else
			System.out.println("존재하지 않는 계좌입니다.");

	}

	static void Withdrawal() { // 계좌 출금
		int inputMoney = 0;
		int index = 0;

		index = AccontIndex(InputAccont());

		if (index != (-1)) {
			System.out.println(" 출금을 선택하셨습니다 ---------");
			System.out.println(" 출금액 : ");
			inputMoney = sc.nextInt();
			sc.nextLine();
			if (Accont.get(index).getinputMoney() < inputMoney)
				System.out.println("총 잔액보다 금액이 적어 출금이 불가능합니다.");
			else
				Accont.get(index).setinputMoney(Accont.get(index).getinputMoney() - inputMoney);

		} else
			System.out.println("입력하신 계좌는 존재하지 않습니다.");
	}

	static boolean DepositLimitChk(int inputMoney) { // 입금 제한 체크
		boolean bchk = false;
		if ((inputMoney < 10) || (inputMoney > 1000000))
			bchk = false;
		else
			bchk = true;
		return bchk;
	}
}
