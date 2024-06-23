# `wordmeat` - practice sentences for learning foreign languages

Because vocabulary acquisition should be as simple as possible - but no
simpler.

Foreign language learning researcher **Dr. Paul Nation** has
[long championed the use of extensive reading](https://www.youtube.com/watch?v=FlJj8vpJxfE)
for the acquisition of vocabulary in the new context. `wordmeat` attempts
to *supplement* this practice by providing a straightforward way to
generate
**high-quality practice sentences** from your target language to
English, using the magic of 
[the OpenAI API](https://openai.com/api/). 

`wordmeat` defaults to **10 sentences
per word**, based on his estimation that learning a new vocabulary word
from high-quality extensive reading requires 7 to 8 meetings. We can't
give you that, you'll have to spend plenty of time actually reading
authentic material to get there -- but we *can* get you started with
something a little more comprehensive (and, dare we say, fun!) than a 
basic dictionary flashcard.

## Quickstart

### Building from source

You will need:

- [OpenJDK](https://openjdk.org/), version 22 or higher; and
- [Apache Maven](https://maven.apache.org/), version 3.8.7 or higher.

If you want to run the test suite, you will also need
[JUnit](https://junit.org/), version 5.8.1 or higher.

You may also find 
[IntelliJ IDEA](https://www.jetbrains.com/idea/download/?fromIDE=)
Community Edition or higher useful as well, as this project can be 
opened directly in IntelliJ and ran using its GUI interface. Here, we
will simply show you a command line usage of it.

First clone this repo:

```bash
git clone https://github.com/hiAndrewQuinn/wordmeat.git
cd wordmeat/
```

Next, use Maven to build and run the program.

```bash
# while cd'ed into wordmeat/
mvn clean install
```

Then you can run the program with

```bash
mvn exec:java -Dexec.mainClass="siilikuin.finstem.Main"
```

This will print the default, and ask you (if you haven't already) to
set an `OPENAI_API_KEY` variable. You can get one of these by going to 
[https://platform.openai.com/api-keys](https://platform.openai.com/api-keys).

```
Target Language: Finnish (fi)
Word: sana
The environment variable OPENAI_API_KEY is not set.
Please set the OPENAI_API_KEY environment variable to your OpenAI API key.

Examples:

  export OPENAI_API_KEY=your_openai_api_key_here              # Unix-based
  setx OPENAI_API_KEY "your_openai_api_key_here"              # Windows
```


`wordmeat` uses
[picocli](https://picocli.info/)
under the hood for its command-line interface. To actually get the
program to do anything useful, you need to specify at least 2 things:

- A `--word`, and
- A `--target-language`, where the target language in question is given 
  by its
  [IETF primary language subtag](https://en.wikipedia.org/wiki/IETF_language_tag).

For example:

```bash
mvn exec:java -Dexec.mainClass="siilikuin.finstem.Main" \
    -Dexec.args="--target-language es casa"
```

You can see the full command line options by running

```bash
mvn exec:java -Dexec.mainClass="siilikuin.finstem.Main" \
    -Dexec.args="--help"
```

If you wish to run the test suite as well, you can do so with
a simple

```bash
mvn test
```

Eventually we plan to **ship this as a native executable** using
something like
[GraalVM](https://www.graalvm.org/). For now, this is the most
foolrpoof method to run this program.

## Contributing

Contributions are welcome! Please read 
[CONTRIBUTING.md](./CONTRIBUTING.md) for details on 
the code of conduct and the process for submitting pull requests.

## License

This project is licensed under
[CC0](https://creativecommons.org/public-domain/cc0/).