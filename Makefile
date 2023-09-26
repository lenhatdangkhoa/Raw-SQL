cl:
	javac -d bin -cp bin uga/cs4370/mydb/Type.java
	javac -d bin -cp bin uga/cs4370/mydb/Cell.java
	javac -d bin -cp bin uga/cs4370/mydb/Predicate.java
	javac -d bin -cp bin uga/cs4370/mydb/Relation.java
	javac -d bin -cp bin uga/cs4370/mydb/RA.java
	javac -d bin -cp bin uga/cs4370/mydb/RelationBuilder.java
	javac -d bin -cp bin uga/cs4370/mydb/impl/RelationImp.java
	javac -d bin -cp bin uga/cs4370/mydb/impl/RelationBuilderImpl.java
	javac -d bin -cp bin uga/cs4370/mydb/impl/RAImp.java
	javac -d bin -cp bin uga/cs4370/mydb/impl/Main.java
run: cl
	java -cp bin uga.cs4370.mydb.impl.Main