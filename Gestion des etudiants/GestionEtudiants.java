import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GestionEtudiants extends JFrame implements ActionListener {
    // La liste des étudiants est stockée dans un ArrayList
    private ArrayList<Etudiant> etudiants;
    // Le modèle de la table par défaut est utilisé pour stocker les données des
    // étudiants
    private DefaultTableModel tableModel;
    // La table JTable affiche les données des étudiants sous forme de tableau
    private JTable table;

    public GestionEtudiants() {
        // Initialisation de la liste des étudiants et du modèle de table par défaut
        etudiants = new ArrayList<Etudiant>();
        tableModel = new DefaultTableModel();
        // Ajout de trois colonnes pour le nom, le prénom et la note de chaque étudiant
        tableModel.addColumn("Nom");
        tableModel.addColumn("Prénom");
        tableModel.addColumn("Note");
        // Création de la table JTable à partir du modèle de table par défaut
        table = new JTable(tableModel);
        // Création d'un JScrollPane pour ajouter la table à la fenêtre principale
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Création d'un JPanel pour les boutons d'action
        JPanel buttonPanel = new JPanel();
        // Utilisation d'un BoxLayout pour afficher les boutons de manière verticale
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        // Création d'un bouton pour ajouter un étudiant
        JButton ajouterButton = new JButton("Ajouter un étudiant");
        ajouterButton.addActionListener(this);
        buttonPanel.add(ajouterButton);
        // Création d'un bouton pour afficher la liste des étudiants
        JButton afficherButton = new JButton("Afficher la liste des étudiants");
        afficherButton.addActionListener(this);
        buttonPanel.add(afficherButton);
        // Création d'un bouton pour trier la liste des étudiants par note
        JButton trierButton = new JButton("Trier la liste des étudiants par note");
        trierButton.addActionListener(this);
        buttonPanel.add(trierButton);
        // Création d'un bouton pour quitter l'application
        JButton quitterButton = new JButton("Quitter");
        quitterButton.addActionListener(this);
        buttonPanel.add(quitterButton);
        // Ajout du JPanel des boutons à la fenêtre principale
        add(buttonPanel, BorderLayout.WEST);

        // Paramétrage de la fenêtre principale
        setTitle("Gestion des étudiants");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Cette méthode gère les actions déclenchées par les boutons du programme.
     * Elle utilise un switch statement pour vérifier quelle action a été
     * déclenchée,
     * et exécute le code approprié en fonction de l'action.
     * 
     * @param e L'événement d'action qui a déclenché la méthode.
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ajouter un étudiant":
                // Ouvre des boîtes de dialogue pour obtenir les informations de l'étudiant à
                // ajouter.
                String nom = JOptionPane.showInputDialog("Entrez le nom de l'étudiant : ");
                String prenom = JOptionPane.showInputDialog("Entrez le prénom de l'étudiant : ");
                double note = Double.parseDouble(JOptionPane.showInputDialog("Entrez la note de l'étudiant : "));
                // Crée un nouvel objet Etudiant avec les informations obtenues.
                Etudiant etudiant = new Etudiant(nom, prenom, note);
                // Ajoute l'objet Etudiant à la liste des étudiants.
                etudiants.add(etudiant);
                // Ajoute une nouvelle ligne à la table pour afficher les informations de
                // l'étudiant ajouté.
                tableModel.addRow(new Object[] { nom, prenom, note });
                break;
            case "Afficher la liste des étudiants":
                // Affiche une boîte de dialogue contenant une JScrollPane de la table des
                // étudiants.
                JOptionPane.showMessageDialog(this, new JScrollPane(table));
                break;
            case "Trier la liste des étudiants par note":
                // Trie la liste des étudiants par ordre croissant de note en utilisant un
                // Comparator anonyme.
                Collections.sort(etudiants, new Comparator<Etudiant>() {
                    public int compare(Etudiant e1, Etudiant e2) {
                        return Double.compare(e1.getNote(), e2.getNote());
                    }
                });
                // Vide la table actuelle pour pouvoir afficher la liste triée.
                tableModel.setRowCount(0);
                // Ajoute chaque étudiant trié à la table.
                for (Etudiant e3 : etudiants) {
                    tableModel.addRow(new Object[] { e3.getNom(), e3.getPrenom(), e3.getNote() });
                }
                break;
            case "Quitter":
                // Quitte le programme.
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        // Création d'une nouvelle instance de la classe GestionEtudiants
        new GestionEtudiants();
    }

    class Etudiant {
        private String nom;
        private String prenom;
        private double note;

        // Constructeur de la classe Etudiant
        public Etudiant(String nom, String prenom, double note) {
            this.nom = nom;
            this.prenom = prenom;
            this.note = note;
        }

        // Méthode pour retourner le nom de l'étudiant
        public String getNom() {
            return nom;
        }

        // Méthode pour retourner le prénom de l'étudiant
        public String getPrenom() {
            return prenom;
        }

        // Méthode pour retourner la note de l'étudiant
        public double getNote() {
            return note;
        }
    }
}