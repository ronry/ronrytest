// See README.txt for information and build instructions.
package com.ronrytest.protobuf;

import java.io.FileInputStream;

import com.ronrytest.protobuf.AddressBookProtos.AddressBook;
import com.ronrytest.protobuf.AddressBookProtos.Person;

class ListPeople {
	// Iterates though all people in the AddressBook and prints info about them.
	static void Print(AddressBook addressBook) {
		for (Person person : addressBook.getPersonList()) {
			System.out.println("Person ID: " + person.getId());
			System.out.println("  Name: " + person.getName());
			System.out.println("  Sex: " + person.getSex());
			if (person.hasEmail()) {
				System.out.println("  E-mail address: " + person.getEmail());
			}

			for (Person.PhoneNumber phoneNumber : person.getPhoneList()) {
				switch (phoneNumber.getType()) {
				case MOBILE:
					System.out.print("  Mobile phone #: ");
					break;
				case HOME:
					System.out.print("  Home phone #: ");
					break;
				case WORK:
					System.out.print("  Work phone #: ");
					break;
				}
				System.out.println(phoneNumber.getNumber());
			}
		}
	}

	// Main function: Reads the entire address book from a file and prints all
	// the information inside.
	public static void main(String[] args) throws Exception {

		String addressFilePath = "/home/ronry/address2.txt";

		// Read the existing address book.
		AddressBook addressBook = AddressBook.parseFrom(new FileInputStream(
				addressFilePath));

		Print(addressBook);
	}
}
