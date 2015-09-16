# ulife test

## /palindrome Detector de palindromos. 

Na pasta raiz encontra-se o projeto empacotado (palindrome.jar).

Para executá-lo, execute o comando "java -jar palindrome.jar [LISTA DE PALAVRAS]"

## /spellcheck Detector de erros ortográficos

Na pasta raiz encontra-se o projeto empacotado (spellcheck.jar). 

Para executá-lo, execute o comando "java -jar spellcheck.jar [LISTA DE FRASES]". Frases são delimitadas por aspas. Ex.: "Isto é uma frase.".

## /spellbattle Batalha ortográfica

Suba um servidor contendo a aplicação (spellbattle.war), com as seguintes system properties:

consumer.key=APP-CONSUMER-KEY

consumer.secret=APP-SECRET

oauth.token=USER TOKEN

oauth.secret=USER SECRET

Faça requisições POST enviando um JSON no formato {"time":N_SEGUNDOS}, com o header Content-Type = "application/json".
