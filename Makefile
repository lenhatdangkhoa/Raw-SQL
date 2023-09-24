cl:
	javac -d bin -cp bin uga/cs4370/mydb/Type.java
	javac -d bin -cp bin uga/cs4370/mydb/Cell.java
	javac -d bin -cp bin uga/cs4370/mydb/Predicate.java
	javac -d bin -cp bin uga/cs4370/mydb/Relation.java
	javac -d bin -cp bin uga/cs4370/mydb/RA.java
	javac -d bin -cp bin uga/cs4370/mydb/RelationBuilder.java
	javac -d bin -cp bin uga/cs4370/mydb/RelationImp.java
run: cl
	java -cp bin uga.cs4370.mydb.impl.Main