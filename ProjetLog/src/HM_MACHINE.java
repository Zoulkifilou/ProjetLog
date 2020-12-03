import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;


public class HM_MACHINE extends JFrame {

	Configuration_de_Donnees cd;
	JTextArea Affiche_data = new JTextArea();
	private String MaRecherche = "-";
	private static boolean update = false;
	String Path_source = "-";
	String Path_dest = "-";
	
	public void parcours(String[] tab_fichier) {
		int k;
		for(k = 0; k<tab_fichier.length; k++) {
			System.out.println(tab_fichier[k]);
//			Affiche_data.setText(" ");
			Affiche_data.append(tab_fichier[k]);
			Affiche_data.append("\n");
			
		}
	}
	
    public HM_MACHINE() {
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle ("ANALOG PROJECT");
        this.setLocationRelativeTo(null);
        cd = new Configuration_de_Donnees();
        

        //Ajout d'une barre de menu
        JMenuBar barre_menu = new JMenuBar();
        setJMenuBar(barre_menu);

        JMenu menu1 = new JMenu();
        menu1.setText("Fichier");
        barre_menu.add(menu1);

	        JMenuItem Mise_a_jour = new JMenuItem("Mettre"+" \u00e0 "+"jour les donn"+"\u00e9"+"es");
	        menu1.add(Mise_a_jour);
	        Mise_a_jour.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					Update_data();
				    
				}
	        
	        });
	        
	        JMenuItem Parametres = new JMenuItem("Param"+"\u00e8"+"tres");
	        menu1.add(Parametres);
	        Parametres.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						cd.create_configFile();
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					String saveFolder_P = "";
					String sourceFolder_P = "";

					try {
						saveFolder_P = cd.get_saveFolder_path();
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (SAXException ex) {
						ex.printStackTrace();
					} catch (ParserConfigurationException ex) {
						ex.printStackTrace();
					}

					try {
						sourceFolder_P = cd.get_sourceFolder_path();
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (SAXException ex) {
						ex.printStackTrace();
					} catch (ParserConfigurationException ex) {
						ex.printStackTrace();
					}

					JDialog Dialogue = new JDialog();
					Dialogue.setSize(360, 130);
				    Dialogue.setLocationRelativeTo(null);
				    Dialogue.setResizable(false);
				    Dialogue.setTitle("Param"+"\u00e8"+"tres");
				    Dialogue.setLayout(new FlowLayout());
				    Dimension dim = new Dimension(80,20);
				    
				    JLabel Entrer1 = new JLabel();
				    Entrer1.setText("Entrer le chemin du dossier source : ");
				    Dialogue.add(Entrer1);
				    
				    JTextField Source = new JTextField(sourceFolder_P);
				    Source.setPreferredSize(dim);
				    Dialogue.add(Source);
				    
				    JLabel Entrer2 = new JLabel();
				    Entrer2.setText("Entrer le chemin du dossier destination : ");
				    Dialogue.add(Entrer2);
				    
				    JTextField Destination = new JTextField(saveFolder_P);
				    Destination.setPreferredSize(dim);
				    Dialogue.add(Destination);
				    
				    JButton OK = new JButton("Ok");
				    Dialogue.add(OK);
				    OK.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							Path_source = Source.getText();
							Path_dest = Destination.getText();
							
							try {
								cd.save_sourceFolder(Path_source);
							} catch (ParserConfigurationException | IOException | SAXException
									| TransformerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							try {
								cd.save_saveFolder(Path_dest);
							} catch (ParserConfigurationException | IOException | SAXException
									| TransformerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							Dialogue.dispose();
							
						}	
			        });
				    
				    
					Dialogue.setVisible(true);
				}	
	        });
	
	        JMenuItem Quitterapp = new JMenuItem("Quitter PLS");
	        menu1.add(Quitterapp);
	        Quitterapp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}	
	        });

        JMenu menu2 = new JMenu();
        menu2.setText("?");
        barre_menu.add(menu2);

	        JMenuItem About = new JMenuItem("A propos de PLS");
	        menu2.add(About);
	        About.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Affiche_data.setText(" ");
					Affiche_data.append("Le syst�me PLS a �t� cr�e en 2019 par la soci�t� ETGL, filiale du groupe Afti.");
					Affiche_data.append("\n");
					Affiche_data.append("PLS est une solution logicielle pour l'exploitation des fichiers de log, compatibles");
					Affiche_data.append("\n");
					Affiche_data.append("avec les formats des serveurs Apache et Microsoft Internet Information Services 5.0.");
				}	
	        });
	
	        JMenuItem Help = new JMenuItem("Aide");
	        menu2.add(Help);
	        Help.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Affiche_data.setText(" ");
					Affiche_data.append("Pour toute interrogation sur le fonctionnement de l'interface PLS, veuillez");
					Affiche_data.append("\n");
					Affiche_data.append("vous reporter au manuel utilisateur fourni avec le logiciel (Manuel_Utilisateur.text)");
					Affiche_data.append("\n");
				}	
	        });



        //Ajout d'un JPanel Bandeau au Nord
        JPanel JPBandeau = new JPanel();
        add(JPBandeau,"North");
        //JPBandeau.setBorder(BorderFactory.createLineBorder(Color.black));

	        //Ajout d'un JLabel
	        JLabel title = new JLabel();
	        title.setText("AnaLOG");
	        title.setFont(new Font("Eras", Font.BOLD, 30));
	        //title.setBorder(BorderFactory.createLineBorder(Color.black));
	        JPBandeau.add(title);


        //Ajout d'un JPanel Bandeau au Sud
        JPanel JPBandeauS = new JPanel();
        add(JPBandeauS,"South");
        //JPBandeauS.setBorder(BorderFactory.createLineBorder(Color.black));

	        //Ajout d'un JLabel
	        JLabel titleS = new JLabel();
	        titleS.setText(" 2020 by ETGL ");
	        titleS.setFont(new Font("Eras", Font.ITALIC, 12));
	        JPBandeauS.add(titleS);


        //Ajout d'un JPanel au Centre
        JPanel JPAffiche = new JPanel();
        JPAffiche.setBackground(Color.white);
        add(JPAffiche, "Center");
        	
        	//Ajout d'un JTextArea pour aficher les donn�es
	       	JPAffiche.add(Affiche_data);
        

        //Ajout d'un JPanel pour la recherche à L'Ouest
        JPanel JPRecherche = new JPanel();
        JPRecherche.setBackground(Color.gray);
//      JPRecherche.setSize(new Dimension(200,0));
        JPRecherche.setPreferredSize(new Dimension((int) (getWidth()/5),0));
        JPRecherche.setLayout(new BoxLayout(JPRecherche, BoxLayout.PAGE_AXIS));
        add(JPRecherche, "West");

	        //Ajout d'un JLabel titre
	        JLabel R_titre = new JLabel("Rechercher des donn"+"\u00e9"+"es ");
	        R_titre.setOpaque(false);
	        R_titre.setFont(new Font("Calibri", Font.BOLD, 18));
	        R_titre.setForeground(Color.white);
	        R_titre.setAlignmentX(JPanel.LEFT_ALIGNMENT);
	        Border margin = new EmptyBorder(5,5,10,0);
        	R_titre.setBorder(margin);
	        JPRecherche.add(R_titre);

	        //Ajout d'un JLabel R_annee
	        JLabel R_annee = new JLabel();
	        R_annee.setText("Choisissez une ann"+"\u00e9"+"e :");
	        R_annee.setFont(new Font("Calibri", Font.PLAIN, 14));
	        R_annee.setForeground(Color.white);
	        R_annee.setAlignmentX(Component.LEFT_ALIGNMENT);
	        R_annee.setBorder(margin);
	        JPRecherche.add(R_annee);

//			//Ajout d'un JComboBox pour l'année
	        JComboBox<String> Recherche_annee = new JComboBox<String>();
	        Recherche_annee.addItem("-");
	        Recherche_annee.addItem("2019");
	        Recherche_annee.addItem("2020");
	        Recherche_annee.setAlignmentX(JPanel.LEFT_ALIGNMENT);
	        Recherche_annee.setMaximumSize(new Dimension(Integer.MAX_VALUE, Recherche_annee.getMinimumSize().height));
	        JPRecherche.add(Recherche_annee);

			//Ajout d'un JLabel R_mois
	        JLabel R_mois = new JLabel();
	        R_mois.setText("Choisissez un mois :");
	        R_mois.setFont(new Font("Calibri", Font.PLAIN, 14));
	        R_mois.setForeground(Color.white);
	        R_mois.setAlignmentX(Component.LEFT_ALIGNMENT);
	        Border margin2 = new EmptyBorder(10,5,10,0);
        	R_mois.setBorder(margin2);
	        JPRecherche.add(R_mois);

	        //Ajout d'un JComboBox pour le mois
	        JComboBox<String> Recherche_mois = new JComboBox<String>();
		        Recherche_mois.addItem("-");
		        Recherche_mois.addItem("Toute l'ann"+"\u00e9"+"e");
		        Recherche_mois.addItem("janvier");
		        Recherche_mois.addItem("f"+"\u00e9"+"vrier");
		        Recherche_mois.addItem("mars");
		        Recherche_mois.addItem("avril");
		        Recherche_mois.addItem("mai");
		        Recherche_mois.addItem("juin");
		        Recherche_mois.addItem("juillet");
		        Recherche_mois.addItem("ao"+"\u00fbt"+"t");
		        Recherche_mois.addItem("septembre");
		        Recherche_mois.addItem("octobre");
		        Recherche_mois.addItem("novembre");
		        Recherche_mois.addItem("d"+"\u00e9"+"cembre");
		    Recherche_mois.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		    Recherche_mois.setMaximumSize(new Dimension(Integer.MAX_VALUE, Recherche_mois.getMinimumSize().height));
	        JPRecherche.add(Recherche_mois);
	        
	        //Ajout d'un JLabel R_jour
	        JLabel R_jour = new JLabel();
	        R_jour.setText("Choisissez un jour :");
	        R_jour.setFont(new Font("Calibri", Font.PLAIN, 14));
	        R_jour.setForeground(Color.white);
	        R_jour.setAlignmentX(Component.LEFT_ALIGNMENT);
        	R_jour.setBorder(margin2);
	        JPRecherche.add(R_jour);
	        
	        //Ajout d'un JComboBox pour le jour
	        JComboBox<String> Recherche_jour = new JComboBox<String>();
	        Recherche_jour.addItem("-");
	        Recherche_jour.addItem("Tout le mois");
	        for(int i = 1; i < 32; i++){
	            Recherche_jour.addItem(String.valueOf(i));
	        }
	        Recherche_jour.setAlignmentX(JPanel.LEFT_ALIGNMENT);
	        Recherche_jour.setMaximumSize(new Dimension(Integer.MAX_VALUE, Recherche_jour.getMinimumSize().height));
	        JPRecherche.add(Recherche_jour);

//        //Ajout d'un JLabel R_data
        JLabel R_data = new JLabel();
        R_data.setText("Type de donn"+"\u00e9"+"es :");
        R_data.setFont(new Font("Calibri", Font.PLAIN, 14));
        R_data.setForeground(Color.white);
        R_data.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        
    	R_data.setBorder(margin2);
        JPRecherche.add(R_data);

//        //Ajout d'un groupe de boutons radio


		//Ajout d'un bouton Submit, pour lancer la recherche
        JButton Submit = new JButton("Rechercher");
        Submit.setAlignmentX(JPanel.LEFT_ALIGNMENT);    	
        JPRecherche.add(Submit);
        Submit.addActionListener(new ActionListener() {
			
			@Override
			public  void actionPerformed(ActionEvent e) {
				Affiche_data.setText(" ");
				String mois ="-";
				
				if (Recherche_mois.getSelectedItem().toString() == "janvier") {
					mois = "01";
				}
			
				if (Recherche_mois.getSelectedItem().toString() == "f"+"\u00e9"+"vrier") {
					mois = "02";
				}
				
				if (Recherche_mois.getSelectedItem().toString() == "mars") {
					mois = "03";
				}
				
				if (Recherche_mois.getSelectedItem().toString() == "avril") {
					mois = "04";
				}
   
				if (Recherche_mois.getSelectedItem().toString() == "mai") {
					mois = "05";
				}
				
				if (Recherche_mois.getSelectedItem().toString() == "juin") {
					mois = "06";
				}
				
				if (Recherche_mois.getSelectedItem().toString() == "juillet") {
					mois = "07";
				}
				
				if (Recherche_mois.getSelectedItem().toString() == "ao"+"\u00fbt"+"t") {
					mois = "08";
				}
				
				if (Recherche_mois.getSelectedItem().toString() == "septembre") {
					mois = "09";
				}
				
				if (Recherche_mois.getSelectedItem().toString() == "octobre") {
					mois = "10";
				}
				
				if (Recherche_mois.getSelectedItem().toString() == "novembre") {
					mois = "11";
				}
				
				if (Recherche_mois.getSelectedItem().toString() == "d"+"\u00e9"+"cembre") {
					mois = "12";
				}
				
				Ma_Recherche = "log-"+Recherche_jour.getSelectedItem().toString()+"-"+mois+"-"+						
								Recherche_annee.getSelectedItem().toString()+".txt-treated.txt";
				
				
				Recherche_Fichier Ma_request = new Recherche_Fichier();
				try {
					if (Ma_request.Rechercher(cd.get_saveFolder_path(), Ma_Recherche) == null) {
						
							JDialog DialogueR = new JDialog();
							DialogueR.setSize(170, 100);
						    DialogueR.setLocationRelativeTo(null);
						    DialogueR.setResizable(false);
						    DialogueR.setTitle("Rechercher");
						    DialogueR.setLayout(new FlowLayout(FlowLayout.CENTER));
						    
						    JLabel Message = new JLabel();
						    Message.setText("Donn"+"\u00e9"+"es introuvables");
						    DialogueR.add(Message);
						    DialogueR.setVisible(true);
					}
					else {parcours(Ma_request.Rechercher(cd.get_saveFolder_path(), Ma_Recherche));
					
					}
				
				} catch (IOException | SAXException | ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

        });
        
	
    }

    
    //Recuperer la String representant la requete de fichier de donnees
    public String getMa_Recherche() {
		return Ma_Recherche;
	}
    
    //Recuperer la String du dossier Source
    public String getPath_source() {
		return Path_source;
	}

    //Recuperer la String du dossier Destination
	public String getPath_dest() {
		return Path_dest;
	}


	//Pop-Up de depart, pour mettre a jour les fichiers
    public void Update_data() {
    	
    	int reponse1 = JOptionPane.showOptionDialog(null,
    			"Souhaitez-vous mettre"+" \u00e0 "+"jour les donn"+"\u00e9"+"es ?","Mise"+" \u00e0 "+"jour",
    			JOptionPane.YES_NO_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, null, null);
    	
    	if (reponse1 == JOptionPane.YES_OPTION) {
    		
    		cd = new Configuration_de_Donnees();
    		String path_lastFile = " ";
    		Ouverture_de_Fichiers OF = new Ouverture_de_Fichiers();
    		File[] Dossier_source = null;
			try {
				Dossier_source = new File(cd.get_sourceFolder_path()).listFiles();
			} catch (IOException | SAXException | ParserConfigurationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
    		
    			for(int i = 0; i < Dossier_source.length; i++){
    			
    				if (Dossier_source[i].isDirectory() == true) {
    		    		File [] F = null;
						F = new File(Dossier_source[i].toString()).listFiles();
    					
    					try {
							if (OF.getLastFile().equals("---")) {
								
								for (int j = 0; j< F.length; j ++) {
									//R�cup�rer les donn�es dans un fichier pr�trait�
									System.out.println(F[j].toString());
									OF.OuvrirFichierPreTraite(F[j].toString());
									//Faire les calculs statistiques
									Calcul_Statistiques CS = new Calcul_Statistiques();
//								    	CS.CompteurIP(OF.getTabDatas());
										
								    	System.out.println(OF.getTabDatas().size());
//								    	CS.CompteurHitIp(OF.getTabDatas());
//								    	CS.CompteurUrlSource(OF.getTabDatas());
//								    	CS.CompteurUrl(OF.getTabDatas());
//								    	CS.CompteurIp_Nav(OF.getTabDatas());
//								    	CS.CompteurIp_Os(OF.getTabDatas());
//								    	CS.CompteurKo(OF.getTabDatas());
							    	
								    	Statistiques st = new Statistiques(); 
								        st.setDixPremIpH(CS.CompteurHitIp(OF.getTabDatas()));
								        st.setDixPremIpK(CS.CompteurKo(OF.getTabDatas()));
								        st.setNbIpDif(CS.CompteurIP(OF.getTabDatas()));
								        st.setDixPremUrl(CS.CompteurUrl(OF.getTabDatas()));
								        st.setDixPremUrlSource(CS.CompteurUrlSource(OF.getTabDatas()));
								        st.setNavigateur(CS.CompteurIp_Nav(OF.getTabDatas()));
								        st.setOs(CS.CompteurIp_Os(OF.getTabDatas()));
							        
							    	//Ecrire un fichier de donn�es trait�es
								    Gestion_de_Fichiers MF = new Gestion_de_Fichiers();
								    MF.EcrireFichier(CS.createStatisticsFile(st), F[j].getName()+"-treated", cd.get_saveFolder_path());
								 	path_lastFile = F[j].getName();
								}
							}		

//							else {	
//									int indiceLastFile;
//									for (int k = 0; k < F.length; k ++) {
//
//										if (OF.getLastFile().equals(F[k].toString())) {
//											indiceLastFile = k;
//											for (int l = indiceLastFile; l < F.length; l ++) {
//												//R�cup�rer les donn�es dans un fichier pr�trait�
//												System.out.println(F[l].toString());
//												OF.OuvrirFichierPreTraite(F[l].toString());
//												//Faire les calculs statistiques
//												
//												Calcul_Stat CS = new Calcul_Stat();
////											    	CS.CompteurIP(OF.getTabDatas());
////											    	CS.CompteurHitIp(OF.getTabDatas());
////											    	CS.CompteurUrlSource(OF.getTabDatas());
////											    	CS.CompteurUrl(OF.getTabDatas());
////											    	CS.CompteurIp_Nav(OF.getTabDatas());
////											    	CS.CompteurIp_Os(OF.getTabDatas());
////											    	CS.CompteurKo(OF.getTabDatas());
//										    	
//										    	Statistics st = new Statistics(); 
//											        st.setDixPremIpH(CS.CompteurHitIp(OF.getTabDatas()));
//											        st.setDixPremIpK(CS.CompteurKo(OF.getTabDatas()));
//											        st.setNbIpDif(CS.CompteurIP(OF.getTabDatas()));
//											        st.setDixPremUrl(CS.CompteurUrl(OF.getTabDatas()));
//											        st.setDixPremUrlSource(CS.CompteurUrlSource(OF.getTabDatas()));
//											        st.setNavigateur(CS.CompteurIp_Nav(OF.getTabDatas()));
//											        st.setOs(CS.CompteurIp_Os(OF.getTabDatas()));
//											       
//										        
//										    	//Ecrire un fichier de donn�es trait�es
//											   
//											    Manage_Files MF = new Manage_Files();
//											    MF.EcrireFichier(CS.createStatisticsFile(st), F[k].getName()+"-treated", cd.get_saveFolder_path());
//											    path_lastFile = F[k].getName();
//											}
//										}
//
//									}
//							}
//							cd.save_lastTreatedFolder(path_lastFile);
							
						} catch (ParserConfigurationException | IOException | SAXException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    					
//			try {
//				try {
//					cd.save_lastTreatedFolder(path_lastFile);
//				} catch (ParserConfigurationException | IOException | SAXException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			} catch (TransformerException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//    		
				
				
			
		    
		    JDialog DialogueM = new JDialog();
			DialogueM.setSize(170, 120);
		    DialogueM.setLocationRelativeTo(null);
		    DialogueM.setResizable(false);
		    DialogueM.setTitle("Mise"+" \u00e0 "+"jour");
		    DialogueM.setLayout(new FlowLayout());
		    
		    JLabel Message = new JLabel();
		    Message.setText("Donn"+"\u00e9"+"es mises"+" \u00e0 "+"jour.");
		    DialogueM.add(Message);
		    
		    JButton OK = new JButton("Ok");
		    DialogueM.add(OK);
		    OK.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					DialogueM.dispose();
				}	
	        });
    		DialogueM.setVisible(true);			
		}
    }
    }	
    }
  
	//Main de test
    public static void main(String[] args) {
        HM_MACHINE C_PLS = new HM_MACHINE();
        C_PLS.setVisible(true);
        C_PLS.Update_data();   
        
    }


}