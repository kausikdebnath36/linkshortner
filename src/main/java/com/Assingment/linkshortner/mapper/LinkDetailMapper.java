package com.Assingment.linkshortner.mapper;

import com.Assingment.linkshortner.dto.LinkDetailDto;
import com.Assingment.linkshortner.entity.LinkDetail;

public class LinkDetailMapper {
	
		public static LinkDetailDto maptoLinkDetailDto(LinkDetail linkdetail) {
			LinkDetailDto linkdetaildto= new LinkDetailDto(
					linkdetail.getId(),
					linkdetail.getOriginalUrl(),
					linkdetail.getShortUrl(),
					linkdetail.getExpiryDate(),
					linkdetail.getCreatedAt(),
					linkdetail.getExpiryDate()
					);
			return linkdetaildto;
		}

	public static LinkDetail maptoLinkDetail(LinkDetailDto linkdetaildto) {
		 		LinkDetail linkdetail= new LinkDetail(
		 				linkdetaildto.getId(),
		 				linkdetaildto.getOriginalUrl(),
		 				linkdetaildto.getShortUrl(),
		 				linkdetaildto.getExpiryDate(),
		 				linkdetaildto.getCreatedAt(),
		 				linkdetaildto.getExpiryDate()
		 				);
		return linkdetail;
	}
}
