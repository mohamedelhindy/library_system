error id: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/SignupPage.java
file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/SignupPage.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[1,9]

error in qdox parser
file content:
```java
offset: 9
uri: file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/SignupPage.java
text:
```scala
private b@@oolean emailExists(String email) {
    String sql = "SELECT user_id FROM users WHERE email = ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, email);
        ResultSet result = statement.executeQuery();

        return result.next();

    } catch (Exception e) {
        e.printStackTrace();
        return true;
    }
}

private boolean signupUser(String username, String email, String password, String role) {
    String sql = "INSERT INTO users (name, email, password_hash, role) VALUES (?, ?, ?, ?)";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.setString(4, role);

        int rows = statement.executeUpdate();

        if (rows > 0) {
            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                int userId = keys.getInt(1);
                insertRole(connection, userId, role);
            }

            return true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}

private void insertRole(Connection connection, int userId, String role) throws Exception {
    String sql = "";

    if (role.equals("Admin")) {
        sql = "INSERT INTO admins (user_id) VALUES (?)";
    } else if (role.equals("Librarian")) {
        sql = "INSERT INTO librarians (user_id) VALUES (?)";
    } else {
        sql = "INSERT INTO members (user_id) VALUES (?)";
    }

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, userId);
        statement.executeUpdate();
    }
```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:562)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:693)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:690)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
	scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:690)
	scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:942)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	java.base/java.lang.Thread.run(Thread.java:840)
```
#### Short summary: 

QDox parse error in file:///C:/Users/Gana/Downloads/library_system-main/library_system/library_system/pages/SignupPage.java