# Archi_Log


Framework de logging créé par Christophe ARRESTIER, Thibaut DA SILVA et Pierre-Jean MESCLE élèves en 4ème année à l’ESIEA.
Pour l’installation de ce framework il suffit d’ajouter la jar au build path de votre projet.
ATTENTION !!

Pour l’écriture des logs en base de données un plugin JDBC est requis ainsi que le SGBD correspondant.

Pour la lecture du contenu de la base cela peut se faire via le plugin sqlite manager de firefox. Pour cela une fois installé il faut cliquer sur « connecter une base de données »  puis naviguer dans l’arborescence jusqu’au repertoire du projet. Ensuite il faut cliquer sur afficher tous les fichiers afin que le .db apparaisse et puisse être ouvert. Une fois ceci fait il faut dérouler le menu Tables cliquer sur LOG puis sur l’onglet Parcourir et rechercher.

Ce framework gère actuellement 3 niveaux de log : Debug, Error et Info.

L’initialisation du logger se fait de la façon suivante : 
		Logger monLog = LoggerFactory.getLogger(maClasse.class, level);
Avec monLog le nom de votre logger, maClasse le nom de la classe dans laquelle vous utilisez le logger et level le niveau de log souhaité.
Enfin, lorsque vous souhaitez afficher vos logs il suffit d’utiliser les méthodes suivantes :
monLog.debug(«votre message ») ;
monLog.info(« votre message ») ;
monLog.error(« votre message ») ;

