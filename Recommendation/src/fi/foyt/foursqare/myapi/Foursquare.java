package fi.foyt.foursqare.myapi;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public class Foursquare {

	FoursquareApi foursquareApi = new FoursquareApi(
			"21V5DV21KIEC5EUQUOG2U0XHZ23KKA3KFJLNZRSINHBDOEIH",
			"3CYVXSKJXMNPNTKST23BECQMJFPHRD1YSR4M3LW24ZJWLYX2", "Callback URL");


	public CompactVenue getVenue(String ll, int radius)
			throws FoursquareApiException {
		// First we need a initialize FoursquareApi.

		// foursquareApi.setVersion("20120701");

		// After client has been initialized we can make queries.
		Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll,
				null, null, null, null, 1, null, null, null, null, null,
				radius, null);
		if (result.getResult().getVenues().length != 0) {
			return result.getResult().getVenues()[0];
		}
		return null;
	}

	public String getMainCatID(String id) throws FoursquareApiException {
		Result<Category[]> categories = foursquareApi.venuesCategories();
		for (int i = 0; i < categories.getResult().length; i++) {
			Category mainCat = categories.getResult()[i];

			if (mainCat.getId().equalsIgnoreCase(id)) {
				return mainCat.getId();
			}

			Category[] c2 = mainCat.getCategories();
			for (int j = 0; j < c2.length; j++) {
				Category subCategory = c2[j];
				if(subCategory.getId().equalsIgnoreCase("530e33ccbcbc57f1066bbfe4")){
					return null;
				}
				
				if (subCategory.getId().equalsIgnoreCase(id)) {
					return mainCat.getId();
				}

				Category[] c3 = subCategory.getCategories();
				for (int k = 0; k < c3.length; k++) {
					Category subSubCategory = c3[k];
					if (subSubCategory.getId().equalsIgnoreCase(id)) {
						return mainCat.getId();
					}
				}

			}
		}
		return null;
	}

}
