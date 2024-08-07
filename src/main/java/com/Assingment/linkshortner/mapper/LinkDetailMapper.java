package com.Assingment.linkshortner.mapper;

import com.Assingment.linkshortner.dto.LinkDetailDto;
import com.Assingment.linkshortner.entity.LinkDetail;

public class LinkDetailMapper {
	
		public static LinkDetailDto maptoLinkDetailDto(LinkDetail linkdetail) {
			LinkDetailDto linkdetaildto= new LinkDetailDto(
					linkdetail.getId(),
					linkdetail.getShortUrl(),
					linkdetail.getOriginalUrl(),
					linkdetail.getExpiryDate(),
					linkdetail.getCreatedAt(),
					linkdetail.getUpdatedAt()
					);
			return linkdetaildto;
		}

	public static LinkDetail maptoLinkDetail(LinkDetailDto linkdetaildto) {
		 		LinkDetail linkdetail= new LinkDetail(
		 				linkdetaildto.getId(),
		 				linkdetaildto.getShortUrl(),
						linkdetaildto.getOriginalUrl(),
		 				linkdetaildto.getExpiryDate(),
		 				linkdetaildto.getCreatedAt(),
		 				linkdetaildto.getUpdatedAt()
		 				);
		return linkdetail;
	}
}
