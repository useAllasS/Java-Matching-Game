-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: miniprojet
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `passwd` varchar(255) NOT NULL,
  `score` float DEFAULT NULL,
  `game_date` date DEFAULT NULL,
  `salt` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (0,'nita','ejFG1u7d+VAiJoa+MptALQ==',8.5,'2022-02-15',_binary 'ÀkÍ™ð\Æy0$3¦¡R\ÕU'),(1,'NITA1','GU78xlXM/8cGoIyyN4okqA==',9.1,'2022-02-14',_binary '\Åñ\rÿŸU›‘›OZ\ØN'),(2,'fouad','POQAzkS+csDgrZ5FuR6FdA==',7.3,'2022-02-13',_binary '«\rK\Äd¤¬M£\ì.+¥I'),(3,'salam','GATIgCT7vMC5OyTqGP9sSQ==',6.2,'2022-02-11',_binary 'Xÿ÷\É7\ÓÜŸÿk\â\Õë˜ƒ\â'),(4,'sawsan','KE2tFP8KG4O+GJQXcMtg/A==',8.9,'2022-02-12',_binary '	Ž)µ÷t¬¬dQm†\Úe'),(20,'cdddc','TMwAfJUQytSf8bH65pjsmA==',NULL,NULL,_binary '^;A¿\Ê,\Ýk\×y7Ê¥nz');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `word` varchar(255) NOT NULL,
  `definitions` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `word` (`word`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'LocalDateTime',' java.time.LocalDateTime :une classe qui reprÃ©sente une date et une heure locale.'),(2,'Optional',' java.util.Optional : une classe qui permet de manipuler des objets optionnels.'),(3,'Stream',' java.util.stream.Stream : une classe qui permet de travailler avec des collections de donnÃ©es de maniÃ¨re fonctionnelle.'),(4,'CompletableFuture',' une classe qui reprÃ©sente un calcul asynchrone.'),(5,'Base64','java.util.Base64: ne classe qui permet l encodage et le dÃ©codage de donnÃ©es en Base64.'),(6,'StringJoiner','java.util.StringJoiner : une classe qui permet de joindre des chaÃ®nes de caractÃ¨res ensemble.'),(7,'Collectors','java.util.StringJoiners : une classe qui fournit des implÃ©mentations de collecteurs pour les Streams'),(8,'Flow','java.util.concurrent.Flow : une classe qui permet de travailler avec des flux de donnÃ©es asynchrones'),(9,'MethodHandles',' java.lang.invoke.MethodHandles : une classe qui permet accÃ©der aux variables en mÃ©moire de maniÃ¨re plus efficace.'),(10,'HttpClient',' java.net.http.HttpClient : une classe qui permet d envoyer des requÃªtes HTTP '),(11,'Json','javax.json.Json :  une classe qui permet la manipulation de donnÃ©es JSON'),(12,'StackWalker','java.lang.StackWalker :  une classe qui permet de parcourir la pile des appels de mÃ©thodes'),(13,'Module','java.lang.module.Module : une classe qui permet la gestion des modules Java'),(14,'Var','java.lang.Var : une classe qui permet de dÃ©clarer des variables avec un type infÃ©rÃ©'),(15,'ChronoUnit','une classe qui reprÃ©sente des unitÃ©s de temps pour les calculs de durÃ©e'),(16,'CompactNumberFormat','java.text.CompactNumberFormat :  une classe qui permet de formater des nombres de maniÃ¨re compacte'),(17,'Logger','java.util.logging.Logger :  une classe qui permet de trouver des logueurs pour les applications'),(18,'BufferedReader','BufferedReader: une classe qui permet la lecture de donnÃ©es Ã  partir d un flux de maniÃ¨re plus efficace.');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keywords`
--

DROP TABLE IF EXISTS `keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `keywords` (
  `id` int NOT NULL AUTO_INCREMENT,
  `word` varchar(255) NOT NULL,
  `definitions` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `word` (`word`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keywords`
--

LOCK TABLES `keywords` WRITE;
/*!40000 ALTER TABLE `keywords` DISABLE KEYS */;
INSERT INTO `keywords` VALUES (1,'abstract','A keyword used to define an abstract method or class, which means the method or class is not implemented and must be implemented by a subclass.'),(2,'assert',' A keyword used to make an assertion, which is a statement that is expected to be true at a certain point in the program.'),(3,'boolean',' A keyword used to define a variable of type boolean, which can hold only two values: true or false.'),(4,'break','break: A keyword used to exit a loop or switch statement.'),(5,'byte','byte: A keyword used to define a variable of type byte, which is a 8-bit signed integer.'),(6,'case','case: A keyword used in a switch statement to define a branch of code to be executed if the value of the switch expression matches the value following the case keyword.'),(7,'catch','catch: A keyword used in a try-catch block to define a block of code to be executed if an exception is caught.'),(8,'class','class: A keyword used to define a class.'),(9,'const','const: A keyword in some older versions of Java that is used to declare a constant variable.'),(10,'continue','continue: A keyword used to skip an iteration of a loop and continue with the next iteration.'),(11,'default','default: A keyword used in a switch statement to define a branch of code to be executed if none of the case expressions match the value of the switch expression.'),(12,'enum','enum: A keyword used to define an enumerated type.'),(13,'finally','finally: A keyword used in a try-catch-finally block to define a block of code that will always be executed regardless of whether an exception is caught or not.'),(14,'Annotations','Annotations: A way to add metadata information to a class, method, or variable.'),(15,'Reflection','Reflection: A way to examine and manipulate classes, interfaces, fields and methods at runtime.'),(16,'Lambda expression','Lambda expression: A concise way of representing a single method interface, also known as functional interface.'),(17,'Exception Handling','Exception Handling: A mechanism that allows a program to handle and recover from errors or unexpected conditions.'),(18,'Encapsulation','Encapsulation: A mechanism that allows a class to hide its internal state and behavior from other parts of the program.');
/*!40000 ALTER TABLE `keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `methods`
--

DROP TABLE IF EXISTS `methods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `methods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `word` varchar(255) NOT NULL,
  `definitions` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `word` (`word`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `methods`
--

LOCK TABLES `methods` WRITE;
/*!40000 ALTER TABLE `methods` DISABLE KEYS */;
INSERT INTO `methods` VALUES (1,'add(','public void add(int index, E element): A method in the java.util.ArrayList class that is used to add an element to a specific position in the list.'),(2,'hasNext()','public boolean hasNext(): A method in the java.util.Iterator interface that is used to check if there is a next element in the iterator.'),(3,'toArray()','public Object[] toArray(): A method in the java.util.HashSet class that is used to convert the set to an array.'),(4,'substring()','public String substring(int beginIndex, int endIndex): A method in the java.lang.String class that is used to get a substring of the current string.'),(5,'addAll()','public boolean addAll(Collection<? extends E> c): A method in the java.util.ArrayList class that is used to add all elements of a collection to the list.'),(6,'retainAll()','public boolean retainAll(Collection<?> c): A method in the java.util.HashSet class that is used to remove all elements that are not in a specific collection from the set.'),(7,'notify()','notify() method: A method that wakes up a single thread that is waiting on the object.'),(8,'finalize()','finalize() method: A method that is called by the garbage collector before an object is destroyed.'),(9,'compareTo()','compareTo() method: A method that compares two objects and returns an integer value indicating their relative order.'),(10,'getClass()','getClass() method: A method that returns the Class object of the object on which it is called.'),(11,'clone()','clone() method: A method that creates a copy of an object.'),(12,'forEach()','forEach() method: A method that performs a given action for each element in a stream.'),(13,'length()','length() method: A method that returns the length of an array or a string.'),(14,'map()','map() method: A method that returns a stream consisting of the results of applying a given function to the elements of a stream.'),(15,'reduce()','reduce() method: A method that applies a binary operator to the elements of a stream and returns an Optional describing the reduced value, if any.'),(16,'concat()','concat() method: A method that concatenates two streams into a single stream.'),(17,'remove()','public void remove(): A method in the java.util.Iterator interface that is used to remove the current element from the iterator.'),(18,'matches()','public boolean matches(String regex): A method in the java.lang.String class that is used to check if the current string matches a specific regular expression.');
/*!40000 ALTER TABLE `methods` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-30 19:38:20
