import controller.admin.AdminTransactions;
import controller.client.ClientTransactions;
import model.clients.Client;
import model.clients.IndividualClient;
import model.clients.LegalClient;
import utils.AccountsType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AdminTransactions adminTransactions = new AdminTransactions();
        ClientTransactions clientTransactions = new ClientTransactions();

        // Create Clients
        IndividualClient individualClient = adminTransactions.createNaturalPerson();
        adminTransactions.showClient(individualClient);
        LegalClient legalClient = adminTransactions.createLegalPerson();
        adminTransactions.showClient(legalClient);

        // Create Accounts
        List<Client> clients = new ArrayList<>(Arrays.asList(individualClient, legalClient));
        adminTransactions.createPersonalAccount(individualClient, clients);
        clientTransactions.showAccount(individualClient.getAccounts());
        adminTransactions.createBusinessAccount(legalClient, clients);
        clientTransactions.showAccount(legalClient.getAccounts());

        // Update Accounts
        adminTransactions.updateCommercialName(legalClient.getIdentification(), "knot", clients);
        adminTransactions.showClient(legalClient);
        legalClient.setCommercialName("flow");
        adminTransactions.updateClient(legalClient.getIdentification(), legalClient, clients);
        adminTransactions.showClient(legalClient);

        clientTransactions.makeDeposit(legalClient.getPrimaryAccount(), new BigDecimal(1500));
        clientTransactions.showAccount(legalClient.getAccounts());

        clientTransactions.makeLoan(legalClient.getPrimaryAccount(), BigDecimal.TEN);
        clientTransactions.showAccount(legalClient.getAccounts());

        System.out.println(individualClient);
        System.out.println("New Balance: R$: " + clientTransactions.getBalance(individualClient.getPrimaryAccount()));

        System.out.println(legalClient);
        clientTransactions.changeAccountType(legalClient.getPrimaryAccount(), AccountsType.DEPOSIT);

        System.out.println(individualClient);
        clientTransactions.makeWithdraw(individualClient.getPrimaryAccount(), new BigDecimal(500));

        System.out.println(legalClient);
        clientTransactions.makeWithdraw(legalClient.getPrimaryAccount(), new BigDecimal(250));

        System.out.println(legalClient);
        clientTransactions.showOverdraft(legalClient.getPrimaryAccount());

        System.out.println(legalClient);
        clientTransactions.listStatement(legalClient.getPrimaryAccount());

        System.out.println(individualClient);
        clientTransactions.listStatement(individualClient.getPrimaryAccount());

    }
}
