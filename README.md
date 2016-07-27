# business-card-parser

Solution for "Business Card OCR" from http://asymmetrik.com/programming-challenges/

Requires Maven 3.2.2+ and Java JDK 8. 

Uses the [Apache OpenNLP](https://opennlp.apache.org/) to perform  [Name Entity Recognition (NER)](https://en.wikipedia.org/wiki/Named-entity_recognition) on proper names in data sent as an input. 
   
To use this code clone from github and run a `mvn clean install` from the command line. This will create a:
 
`target/business-card-parser-1.0-SNAPSHOT-jar-with-depencies.jar`. 

In the `target` directory you can run it as follows: 

```
user@hostname ~/path/to/business-card-parser/target$ cat ../src/test/resources/example-arthur-wilson.txt
Arthur Wilson
Software Engineer
Decision & Security Technologies
ABC Technologies
123 North 11th Street
Suite 229
Arlington, VA 22209
Tel: +1 (703) 555-1259
Fax: +1 (703) 555-1200
user@hostname ~/path/to/business-card-parser/target$ java -jar business-card-parser-1.0-SNAPSHOT-jar-with-dependencies.jar
usage: business-card-parser.jar
 -f,--file <arg>   File to parse
 -h,--help         Show help
user@hostname ~/path/to/business-card-parser/target$ java -jar business-card-parser-1.0-SNAPSHOT-jar-with-dependencies.jar -f ../src/test/resources/example-arthur-wilson.txt
Name: Arthur Wilson
Phone: 17035551259
Email: awilson@abctech.com
```