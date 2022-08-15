package controller.admin;

import model.clients.Client;
import model.clients.IndividualClient;
import model.clients.LegalClient;

import java.util.List;

public interface AdminTransactionsInterface {

    LegalClient createLegalPerson();

    IndividualClient createNaturalPerson();

    void createBusinessAccount(LegalClient client, List<Client> clients);

    void createPersonalAccount(IndividualClient client, List<Client> clients);

    void updateClient(String identification, Client client, List<Client> clients);

    void updateCommercialName(String identification, String newName, List<Client> clients);

    void showClient(Client client);
}
