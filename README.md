# bootique-mybatis-demo
Bootique app demonstrating the usage of bootique-mybatis

## Prerequisites
      
    * Java 1.8 or newer.
    * Apache Maven.

## Build the Demo
      
Here is how to build it:
        
    $ git clone https://github.com/bootique-examples/bootique-mybatis-demo.git
    $ cd bootique-mybatis-demo
    $ mvn package

## Run the Demo

To execute the example you should have a database to connect with. You can use Docker to start MySQL.

Here is docker command to start MySQL instance that can be used for this example: 
 
    $ docker run --name mybatis-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=example -e MYSQL_DATABASE=mybatis_demo -d mysql:8.0

Import demo database schema:
 
    $ docker exec -i mybatis-mysql mysql -uroot -pexample mysql < mybatis_example.sql

Now you can check the options available in your app:

    $ java -jar target/bootique.mybatis.demo-2.0.jar 

    NAME
      bootique.mybatis.demo-2.0.jar

    OPTIONS
      -a artistId, --artistId=artistId
           input artistId

      -c yaml_location, --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      --get-all-artists
           Get all artists command

      --get-all-paintings
           Get all paintings command

      --get-artist-by-id
           Get artist by id command. Required option "--id"

      --get-painting-by-id
           Get painting by id command. Required option "--id"

      -h, --help
           Prints this message.

      -H, --help-config
           Prints information about application modules and their configuration options.

      --id=id
           input id to select by id

      --insert-artist
           Insert artist command. Required option "--name"

      --insert-painting
           Insert painting command. Required options "--name" and "--artistId"

      -n name, --name=name
           input name


Insert data into database via --insert-artist command:

    java -jar target/bootique.mybatis.demo-2.0.jar --insert-artist --name="Pablo Picasso"

    Artist{artistId=0, artistName=Pablo Picasso'} Inserted


Select all table via --get-all-artists command:

    $ java -jar target/bootique.mybatis.demo-2.0.jar --get-all-artists

    [Artist{artistId=1, artistName='Pablo Picasso'}, Artist{artistId=2, artistName='Vincent van Gogh'}, Artist{artistId=3, artistName='Andy Warhol'}]

Select one by id via --get-all-artists command:

    $ java -jar target/bootique.mybatis.demo-2.0.jar --get-artist-by-id --id=1 

    Artist{artistId=3, artistName='Pablo Picasso'}





          
