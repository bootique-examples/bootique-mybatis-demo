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
import java.util.List;

public class GetAllArtists extends CommandWithMetadata {

    private Provider<SqlSessionManager> sqlSessionManagerProvider;

    @Inject
    public GetAllArtists(Provider<SqlSessionManager> providerSessionManager) {
        super(CommandMetadata.builder(GetAllArtists.class).description("Get all artists command").build());
        this.sqlSessionManagerProvider = providerSessionManager;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        try (SqlSession sqlSession = sqlSessionManagerProvider.get().openSession()) {
            ArtistMapper mapper = sqlSession.getMapper(ArtistMapper.class);
            List<Artist> artists = mapper.getAllArtists();
            System.out.println(artists.toString());
        }
        return CommandOutcome.succeeded();
    }
}
