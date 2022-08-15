package controller.admin;

import model.account.BusinessAccount;
import model.account.PersonalAccount;
import model.clients.Address;
import model.clients.Client;
import model.clients.IndividualClient;
import model.clients.LegalClient;
import utils.AccountsType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AdminTransactions implements AdminTransactionsInterface {

    private static Scanner read = new Scanner(System.in);

    private Address createClientAddress() {
        System.out.println("-----------ADDRESS---------------");
        System.out.println("District");
        var district = read.nextLine();

        System.out.println("Complement");
        var complement = read.nextLine();

        System.out.println("Number");
        var number = read.nextLine();

        System.out.println("City");
        var city = read.nextLine();

        System.out.println("State");
        var state = read.nextLine();

        return new Address(district, complement, number, city, state);
    }

    private String getNameClient() {
        System.out.println("Name");
        return read.nextLine();
    }

    private String getCellphoneClient() {
        System.out.println("Cellphone");
        return read.nextLine();
    }

    @Override
    public LegalClient createLegalPerson() {
        Address address = createClientAddress();

        System.out.println("-----------LEGAL PERSON---------------");

        var name = getNameClient();
        String cellphone = getCellphoneClient();

        System.out.println("CNPJ");
        var cnpj = read.nextLine();

        System.out.println("Comercial Name");
        var legalName = read.nextLine();

        return new LegalClient(name, cellphone, address, cnpj, legalName);
    }

    @Override
    public IndividualClient createNaturalPerson() {
        Address address = createClientAddress();

        System.out.println("-----------NATURAL PERSON---------------");

        var name = getNameClient();
        String cellphone = getCellphoneClient();

        System.out.println("CPF");
        var cpf = read.nextLine();

        return new IndividualClient(name, cellphone, address, cpf);
    }

    @Override
    public void createPersonalAccount(IndividualClient client, List<Client> clients) {
        Integer number = findNextNumber(clients);

        System.out.println("Balance");
        BigDecimal initialValue = read.nextBigDecimal();

        PersonalAccount account = new PersonalAccount(number, initialValue, getTypeAccount());
        client.addAccounts(account);
    }

    @Override
    public void updateClient(String identification, Client client, List<Client> clients) {
        clients.removeIf(c -> c.getIdentification().compareTo(identification) == 0);
        clients.add(client);
    }

    @Override
    public void updateCommercialName(String identification, String newName, List<Client> clients) {
        clients.forEach(client -> {
            if (client.getIdentification().compareTo(identification) == 0) {
                ((LegalClient) client).setCommercialName(newName);
            }
        });
    }

    private AccountsType getTypeAccount() {
        System.out.println("Type of Account");
        Integer tAcc;

        do {
            System.out.println("1 - CHECKING\n2 - DEPOSIT");
            tAcc = read.nextInt();
        } while (tAcc != 1 && tAcc != 2);

        return tAcc == 1 ? AccountsType.CHECKING : AccountsType.DEPOSIT;
    }

    @Override
    public void createBusinessAccount(LegalClient client, List<Client> clients) {
        System.out.println("Balance");
        BigDecimal initialValue = read.nextBigDecimal();

        Integer number = findNextNumber(clients);
        BusinessAccount buss = new BusinessAccount(number, initialValue);

        client.addAccounts(buss);
    }

    private Integer findNextNumber(List<? extends Client> clients) {
        Integer max = 0;

        for (Client client : clients) {
            Integer next = client.getNextNumberOfAccount();
            max = max > next ? max : next;
        }

        return max;
    }

    @Override
    public void showClient(Client client) {
        System.out.println(client.toString());
    }
}
