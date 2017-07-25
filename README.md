xmpp-chatbot
--

NOTE: THIS PROJECT IS OUT-OF-DATE, USE IT AT YOUR OWN RISK... I mean, fork and contribute! =)

A chatbot using xmpp protocol for chat and ibm watson conversation for bot!

Initial configuration
--
Create a properties file with the keys:

```
org.experimental.chatbot.watson.username=
org.experimental.chatbot.watson.password=
org.experimental.chatbot.watson.workspace=
org.experimental.chatbot.xmpp.username=
org.experimental.chatbot.xmpp.password=
org.experimental.chatbot.xmpp.host=
org.experimental.chatbot.xmpp.port=
```

Start the application
--
```shell
java -jar -Dapp.conf=${properties} xmpp-chatbot-XXX-{jar-with-dependencies}.jar
```
