package org.gmnz.qet.sandbox;


import java.util.TreeSet;


class TestSectionRankCompare {

	public static void main(String[] args) {
		SectionRank sr1 = new SectionRank();
		sr1.sectionName = "Mu";
		sr1.rank = 3;

		SectionRank sr2 = new SectionRank();
		sr2.sectionName = "Aldebaran";
		sr2.rank = 7;

		SectionRank sr3 = new SectionRank();
		sr3.sectionName = "Saga";
		sr3.rank = 2;

//		Set<SectionRank> mySet = new TreeSet<>();
		TreeSet<SectionRank> mySet = new TreeSet<>();
		mySet.add(sr1);
		mySet.add(sr2);
		mySet.add(sr3);

		for (SectionRank sr : mySet) {
			System.out.format("[%s | %d] ", sr.sectionName, sr.rank);
		}
		System.out.println();
		sr3.rank = 5;
		for (SectionRank sr : mySet) {
			System.out.format("[%s | %d] ", sr.sectionName, sr.rank);
		}
		System.out.println();

		SectionRank sr3_1 = new SectionRank();
		sr3_1.sectionName = sr3.sectionName;
		sr3_1.rank = 64;

		System.out.println(sr3.compareTo(sr3_1));
		System.out.println(sr3.equals(sr3_1));


		/*
		 * 
		 * i primi tre contesti con il maggior numero di richieste a cui è stato
		 * risposto un codice 200
		 * 
		 * mappa con insieme delle chiavi ordinato int -> set<String>
		 * 
		 * vediamo se con una mappa bidirezionale potrei risolvere il problema. Io
		 * voglio sapere quante hit200 ha avuto un determinato contesto, e per questo mi
		 * basta una mappatura diretta, che associa ad ogni stringa che identifica un
		 * contesto il numero di hit200 ricevute Ma voglio anche sapere quali sono gli N
		 * contesti con il più alto numero di hit200 registrati fino ad ora. Inoltre,
		 * mentre ad uno specifico contesto può corrispondere ad ogni istante uno ed un
		 * sol valore di hit200, l'opposto in generale non è vero. Infatti se un certo
		 * contesto CX ha ricevuto HX richieste con risposta 200, è vero che ad un
		 * determinato istante ci sia più di un contesto ad aver ricevuto HX richieste
		 * con risposta 200. D'altra parte ordinare secondo il numero di hit con una
		 * certa risposta è esattamente quello che mi serve per determinare
		 * 
		 * 
		 */
	}

}
