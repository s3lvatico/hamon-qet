package org.gmnz.qet.clog;


import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PathsTest {

	void test1() {
		Path p1 = Paths.get(".");
		System.out.println(p1);
		System.out.println(p1.isAbsolute());
		Path p1a = p1.toAbsolutePath();
		System.out.println(p1a);

		Path p2 = Paths.get(".\\../polpette.txt");
		System.out.println(p2);
		System.out.println(p2.isAbsolute());
		Path p2a = p2.toAbsolutePath();
		Path p2an = p2a.normalize();
		System.out.println("p2 absolute: " + p2a);
		System.out.println("p2 absolute normalized: " + p2an);

		FileSystem fs =  FileSystems.getDefault();
		System.out.println(fs);
		Path pBraciole = fs.getPath("braciole.txt");
		System.out.println(pBraciole);
		System.out.println(pBraciole.toAbsolutePath());

	}

	void test2(String targetFileName) {
		/*
			qual è l'idea del procedimento da seguire:
			mi arriva una stringa
			la converto in un path dipendente dal sistema
			se non è un path assoluto lo converto in un path assoluto
			poi provo a creare il file rappresentato da questo path e ci scrivo qualcosa dentro
		 */
		FileSystem fs =  FileSystems.getDefault();
		Path targetPath = fs.getPath(targetFileName);
		System.out.println("path determinato: " + targetPath);
		System.out.println("è assoluto? : " + targetPath.isAbsolute());
		if (!targetPath.isAbsolute()) {
			targetPath = targetPath.toAbsolutePath().normalize();
		}
		System.out.println("target path computed: " + targetPath);
	}

	public static void main(String[] args) {
		PathsTest pt = new PathsTest();
		// pt.test1();
		pt.test2("./../polpetta.dat");

	}

}
