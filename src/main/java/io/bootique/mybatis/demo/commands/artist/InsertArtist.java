package io.bootique.mybatis.demo.commands.artist;

import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import io.bootique.mybatis.demo.model.Artist;
import io.bootique.mybatis.demo.model.mapper.ArtistMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import javax.inject.Inject;
import javax.inject.Provider;

public class InsertArtist extends CommandWithMetadata {

    private Provider<SqlSessionManager> sqlSessionManagerProvider;

    @Inject
    public InsertArtist(Provider<SqlSessionManager> providerSessionManager) {
        super(CommandMetadata.builder("Insert artist")
                .name("insert-artist")
                .description("Insert artist command. Required option \"--name\" ").build());
        this.sqlSessionManagerProvider = providerSessionManager;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        String name;
        if (cli.hasOption("name")) {
            name = cli.optionString("name");
            try (SqlSession sqlSession = sqlSessionManagerProvider.get().openSession()) {
                ArtistMapper mapper = sqlSession.getMapper(ArtistMapper.class);
                Artist artist = new Artist();
                artist.setName(name);
                mapper.insertArtist(artist);
                sqlSession.commit();
                System.out.println(artist.toString() + " Inserted");
            }
            return CommandOutcome.succeeded();
        } else {
            return CommandOutcome.failed(1, "please input \"--name\" ");
        }
    }
}
