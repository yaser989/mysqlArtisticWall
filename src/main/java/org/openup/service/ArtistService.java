package org.openup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openup.DTO.ArtistDto;
import org.openup.entity.Artist;
import org.openup.entity.ArtistDomain;
import org.openup.entity.Role;
import org.openup.mapper.ArtistMapper;
import org.openup.repo.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private ArtistMapper artistMapper;
	
	
	public List<ArtistDto> findAll() {
		List<ArtistDto> listArtistDto = new ArrayList();
        List<Artist> listArtist = artistRepository.findAll();
		for (Artist art : listArtist) {
			listArtistDto.add(artistMapper.artistToDto(art));
		}
		return listArtistDto;
	}
	
	

	public ArtistDto findById(Long id) {
	ArtistDto artistDto = new ArtistDto();
	
	ArtistDomain artistDomai= ArtistDomain.builder().domain(artistDto.getArtistDomain()).build();
	
	Artist toPersist = Artist.builder().name(artistDto.getArtistName()).lastName(artistDto.getArtistLastName())
			.mail(artistDto.getArtistMail()).password(artistDto.getArtistPassword()).photo(artistDto.getArtistPhoto())
			.build();
	
	Role rol = Role.builder().id(artistDto.getRolId()).roleName(artistDto.getArtistRole()).build();
	
	toPersist.setArtistDomain(artistDomai);
	toPersist.setRole(rol);
	Optional<Artist> artists = artistRepository.findById(id);
	if (artists.isPresent()) {
		toPersist = artists.get();
		artistDto.setId(toPersist.getId());
		artistDto.setArtistDomain(toPersist.getArtistDomain().getDomain());
		artistDto.setArtistName(toPersist.getName());
		artistDto.setArtistLastName(toPersist.getLastName());
		artistDto.setArtistMail(toPersist.getMail());
		artistDto.setArtistPassword(toPersist.getPassword());
		artistDto.setArtistRole(toPersist.getRole().getRoleName());
		artistDto.setRolId(toPersist.getRole().getId());
	}
		return artistDto;
	}

	public Artist createNewArtist(ArtistDto artistDto) {
		ArtistDomain artistDomai= ArtistDomain.builder().domain(artistDto.getArtistDomain()).build();
		Role role = new Role();
		Artist toPersist = Artist.builder().name(artistDto.getArtistName()).lastName(artistDto.getArtistLastName())
				.mail(artistDto.getArtistMail()).password(artistDto.getArtistPassword()).photo(artistDto.getArtistPhoto())
				.build();
		role.setRoleName("ROLE_USER");
		toPersist.setRole(role);
		toPersist.setArtistDomain(artistDomai);
//	    toPersist.setPassword(bCryptPasswordEncoder.encode(toPersist.getPassword()));
		System.out.println(toPersist);
		System.out.println(artistDto);
		return artistRepository.save(toPersist);
	}

	public ArtistDto login(String mail, String password) {
		
		Artist authenticatedArtist = artistRepository.findByMail(mail);
		ArtistDto artistDto = new ArtistDto();
		artistDto.setArtistDomain(authenticatedArtist.getArtistDomain().getDomain());
		artistDto.setArtistLastName(authenticatedArtist.getLastName());
		artistDto.setArtistName(authenticatedArtist.getName());
		artistDto.setArtistMail(authenticatedArtist.getMail());
		artistDto.setArtistPassword(authenticatedArtist.getPassword());
		artistDto.setArtistRole(authenticatedArtist.getRole().getRoleName());
		artistDto.setId(authenticatedArtist.getId());
		artistDto.setRolId(authenticatedArtist.getRole().getId());
		
	
	    	return artistDto;
	}
	
	public ArtistDto updatArtist(Long id , ArtistDto artistDto) {
		ArtistDomain artistDomai= ArtistDomain.builder().domain(artistDto.getArtistDomain()).build();
		Artist toPersist = Artist.builder().name(artistDto.getArtistName()).lastName(artistDto.getArtistLastName())
				.mail(artistDto.getArtistMail()).password(artistDto.getArtistPassword()).photo(artistDto.getArtistPhoto())
				.build();
		toPersist.setArtistDomain(artistDomai);
		
		Optional<Artist> artists = artistRepository.findById(id);
		
		if (artists.isPresent()) {
			Artist artis = artists.get();
			artis.setName(toPersist.getName());
			artis.setLastName(toPersist.getLastName());
			artis.setMail(toPersist.getMail());
			artis.setPassword(toPersist.getPassword());
			artis.getArtistDomain().setDomain(artistDomai.getDomain());
			Artist saveArtist = artistRepository.save(artis);
			artistDto.setId(saveArtist.getId());
		}
		return artistDto;
	}



	

}
