package org.openup.mapper;

import org.openup.DTO.ArtistDto;
import org.openup.entity.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {
	
	public ArtistDto artistToDto(Artist artist) {
		ArtistDto artistDto = new ArtistDto();
		artistDto.setId(artist.getId());
		artistDto.setArtistName(artist.getName());
		artistDto.setArtistLastName(artist.getLastName());
		artistDto.setArtistMail(artist.getMail());
		artistDto.setArtistDomain(artist.getArtistDomain().getDomain());
		artistDto.setArtistRole(artist.getRole().getRoleName());
//		artistDto.setArtistPhoto(artist.getPhoto());
		return artistDto;
	}
	
	

}
