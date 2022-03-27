import model.Location;
import model.Trajet;

public class DebitRetour {

    public static void processusDebitMontant(Location locationEnCours, Trajet trajetFromVeloAccroche) {
        System.out.println("Vous avez effectué un trajet de " + trajetFromVeloAccroche.getDuree() + " minutes.\n"
                + "Le montant à débiter qui sera ajouté à votre location est de " + trajetFromVeloAccroche.calculMontantSingleTrajet() + " euros.\n");
        locationEnCours.setMontant((locationEnCours.getMontant()== null ? 0.0f: locationEnCours.getMontant()) + trajetFromVeloAccroche.calculMontantSingleTrajet());

        if (locationEnCours.verifTrajetEnCours() == true) {
            System.out.println("Vous avez encore des trajets en cours.\nUne fois votre location terminée, vous serez débité.e d'un montant de " 
                + locationEnCours.getMontant() + " sur la totalité de vos trajets achevés.");
            // ne pas débiter le montant de la CB du client puisque la location n'est pas terminée et il reste des trajets en cours.
            // Le montant de la location en cours reste constant.
            System.out.println("Merci pour votre confiance et à très bientôt dans nos stations !");
        }
        else {
            System.out.println("Vous n'avez plus de trajets en cours. Le montant de votre location s'élève à " + locationEnCours.getMontant() 
                + " euros. Il vous sera débité dans quelques instants.");
            // débiter le montant de la CB du client puisque la location est terminée (il n'y a plus de trajets en cours).
            locationEnCours.setMontant((float) 0);
            // Le montant de la location en cours est remis à zéro.
            System.out.println("Merci pour votre confiance et à très bientôt dans nos stations !");
        }
    }
}
