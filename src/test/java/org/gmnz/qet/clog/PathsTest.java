package org.gmnz.qet.clog;


import java.nio.file.Path;
import java.nio.file.Paths;


public class PathsTest {

	public static void main(String[] args) {
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
		System.out.println(p2a);
		System.out.println(p2an);

	}

}
