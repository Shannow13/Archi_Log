# Archi_Log


Framework de logging créé par Christophe ARRESTIER, Thibaut DA SILVA et Pierre-Jean MESCLE élèves en 4ème année à l’ESIEA.

Actuellement nous avons un soucis avec le fichier config.properties lorsque nous compressons le framework en .jar. En effet lorsque le .jar est ajouté a un autre projet et que nous essayons d'éxecuter le programme il y a une erreur FileNotFoundException nous spécifiant que le fichier config.properties est introuvable.

Pour l’écriture des logs en base de données un plugin JDBC est requis ainsi que le SGBD correspondant.

Pour la lecture du contenu de la base cela peut se faire via le plugin sqlite manager de firefox. Pour cela une fois installé il faut cliquer sur « connecter une base de données »  puis naviguer dans l’arborescence jusqu’au repertoire du projet. Ensuite il faut cliquer sur afficher tous les fichiers afin que le .db apparaisse et puisse être ouvert. Une fois ceci fait il faut dérouler le menu Tables cliquer sur LOG puis sur l’onglet Parcourir et rechercher.

Ce framework gère actuellement 3 niveaux de log : Debug, Error et Info.

L’initialisation du logger se fait de deux manière : 

		- Logger monLog = LoggerFactory.getLogger(maClasse.class, String level, boolean file, boolean db);
			level : niveau d'affichage des log (debug, info, error)
			file : true pour l'écriture dans le fichier par défaut
			db : true pour l'écriture dans la base de données par défaut
			
		- Logger monLog = LoggerFactory.getLogger(maClass.class, String level, String name, boolean db);
			level : niveau d'affichage des log (debug, info, error)
			name : nom du fichier où écrire les logs
			db : true pour écrire dans la base de données par défaut
			

Enfin, lorsque vous souhaitez afficher vos logs il suffit d’utiliser les méthodes suivantes :
monLog.debug(«votre message ») ;
monLog.info(« votre message ») ;
monLog.error(« votre message ») ;

