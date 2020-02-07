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

public class GetArtistById extends CommandWithMetadata {

    private Provider<SqlSessionManager> sqlSessionManagerProvider;

    @Inject
    public GetArtistById(Provider<SqlSessionManager> providerSessionManager) {
        super(CommandMetadata.builder("Get artist by id")
                .name("get-artist-by-id")
                .description("Get artist by id command. Required option \"--id\" ")
                .build());
        this.sqlSessionManagerProvider = providerSessionManager;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        int id;
        if (cli.hasOption("id")){
            id = Integer.parseInt(cli.optionString("id"));
            try (SqlSession sqlSession = sqlSessionManagerProvider.get().openSession()) {
                ArtistMapper mapper = sqlSession.getMapper(ArtistMapper.class);
                Artist artist = mapper.getArtistById(id);
                System.out.println(artist.toString());
            }
            return CommandOutcome.succeeded();
        } else {
            return CommandOutcome.failed(1, "please input \"--id\"");
        }
    }
}