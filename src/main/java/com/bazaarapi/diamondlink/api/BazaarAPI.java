package com.bazaarapi.diamondlink.api;

import com.bazaarapi.diamondlink.modules.BazaarAPIReader;

public class BazaarAPI {
	public static BazaarAPIReader getBazaarAPIReader() {
		return new BazaarAPIReader();
	}
}
