package com.imagefinder;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import com.imagefinder.exception.DomainNotFoundException;
import com.imagefinder.controller.MainUrlHandler;
import com.imagefinder.services.impl.MainUrlServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.mockito.Mockito;

public class ImageFinderTest {

	public HttpServletRequest request;
	public HttpServletResponse response;
	public StringWriter sw;
	public HttpSession session;

	@Before
	public void setUp() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
    	sw = new StringWriter();
    	PrintWriter pw = new PrintWriter(sw);
		Mockito.when(response.getWriter()).thenReturn(pw);
		Mockito.when(request.getRequestURI()).thenReturn("/foo/foo/foo");
		Mockito.when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/foo/foo/foo"));
		session = Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(session);
	}

  @Test
  public void testMainUrlHandler1_80legs() throws IOException, ServletException, DomainNotFoundException {
		Mockito.when(request.getServletPath()).thenReturn("/main");
		Set<String> expected = new HashSet<>(Arrays.asList("https://80legs.com/wp-content/uploads/2016/05/Lexalytics-Logo-190x60.jpg",
				"https://80legs.com/wp-content/uploads/2016/05/logo_experian-178x60.gif",
				"https://80legs.com/wp-content/uploads/2016/05/logo_discover-235x54.jpg",
				"https://80legs.com/wp-content/uploads/2016/05/logo_citygrid-196x60.png",
				"https://80legs.com/wp-content/uploads/2016/05/Shutterstock_2012_logo-235x34.png",
				"https://80legs.com/wp-content/uploads/2016/06/shutterstock_432153523_2-320x215.jpg",
				"https://80legs.com/wp-content/uploads/2016/06/18r5c2er91sxljpg-320x215.jpg",
				"https://80legs.com/wp-content/uploads/2016/05/shutterstock_281221427_2.jpg",
				"https://80legs.com/wp-content/uploads/2016/05/logo_ncarb-156x60.png",
				"https://80legs.com/wp-content/uploads/2016/05/customer-logos-mailchimp.png",
				"https://80legs.com/wp-content/uploads/2016/05/logo_paypal-213x60.png",
				"https://80legs.com/wp-content/uploads/2016/05/logo_shutterstock-235x43.png",
				"https://80legs.com/wp-content/uploads/2016/05/logo_mailchimp-199x60.jpg",
				"https://80legs.com/wp-content/uploads/2016/05/CityGridMedia_Logo_350w_4162_0-231x60.png",
				"https://80legs.com/wp-content/uploads/2016/05/logo_trendkite-235x35.png",
				"https://80legs.com/wp-content/uploads/2016/05/customer-logos-integral.png",
				"https://80legs.com/wp-content/uploads/2016/05/logo_lexalytics-190x60.jpg"));

	    MainUrlServiceImpl mainUrlService = new MainUrlServiceImpl();
		MainUrlHandler obj = new MainUrlHandler(mainUrlService);
		List<String> actual = obj.getUrl("https://80legs.com/");

		for(int i=0;i<actual.size();i++)
		{
			if(expected.contains(actual.get(i))==false)
			{
				Assert.fail("This particular element was not present in the Expected set "+actual.get(i));
			}
		}

  }


	@Test
	public void testMainUrlHanlder_dexi() throws IOException, ServletException,DomainNotFoundException {
		Mockito.when(request.getServletPath()).thenReturn("/main");
		Set<String> expected = new HashSet<>(Arrays.asList("https://www.dexi.io/wp-content/uploads/2022/03/samsung-s.png", "https://www.dexi.io/wp-content/uploads/2020/04/software_product_compliance.png", "https://dexi.io/wp-content/uploads/2020/04/twitter-icon.png", "https://www.dexi.io/wp-content/uploads/2020/04/Dexi-new-logo-1.svg", "https://www.dexi.io/wp-content/uploads/2020/08/power-bi_logo-4.png", "https://www.dexi.io/wp-content/uploads/2020/08/shelves_A.svg", "https://www.dexi.io/wp-content/uploads/2020/09/london-eye-white-1.png", "https://ws.zoominfo.com/pixel/62f35df9b69a640090385563", "https://dexi.io/wp-content/uploads/2020/04/Layer-2-1-1.png", "https://www.dexi.io/wp-content/uploads/2022/01/footer-logo.png", "https://www.dexi.io/wp-content/uploads/2020/04/Account-and-license-set-up-1.svg", "https://secure.gravatar.com/avatar/ac23be512e73947cea0cbe867b290d35?s=72&d=mm&r=g", "https://www.dexi.io/wp-content/uploads/2022/03/walmart-s.png", "https://www.dexi.io/wp-content/uploads/2022/03/targert-s.png", "https://www.dexi.io/wp-content/uploads/2020/08/why-icon1.png", "https://www.dexi.io/wp-content/uploads/2020/08/brain-B-s-1.png", "https://www.dexi.io/wp-content/uploads/2020/08/power-bi_logo-3.png", "https://www.dexi.io/wp-content/uploads/2022/04/Rectangle-125-1.png", "https://www.dexi.io/wp-content/uploads/2022/04/connectors-1.svg", "https://www.dexi.io/wp-content/uploads/2020/04/services_vision.png", "https://www.dexi.io/wp-content/uploads/2022/02/Laptoppy.webp", "https://www.dexi.io/wp-content/uploads/2020/08/why-icon2.png", "https://www.dexi.io/wp-content/uploads/2022/06/dexi-icon-1.svg", "https://www.dexi.io/wp-content/uploads/2020/08/power-bi_logo-2.png", "https://dexiio.stage.site/wp-content/uploads/2022/01/footer-logo.png", "https://www.dexi.io/wp-content/uploads/2020/09/behhive-1.png", "https://www.dexi.io/wp-content/uploads/2022/04/Layer-2-8.svg", "https://www.dexi.io/wp-content/uploads/2020/04/training-1.svg", "https://www.dexi.io/wp-content/uploads/2020/08/Managed-Services-v2-1.png", "https://www.dexi.io/wp-content/plugins/wpforms/assets/images/submit-spin.svg", "https://www.dexi.io/wp-content/uploads/2022/03/vodafone-s.png", "https://www.dexi.io/wp-content/uploads/2022/03/mattel-s.png.png", "https://www.dexi.io/wp-content/uploads/2020/04/Layer-2-8.svg", "https://www.dexi.io/wp-content/uploads/2020/08/Rectangle-132.png", "https://www.dexi.io/wp-content/uploads/2022/01/Rectangle-153.png", "https://www.dexi.io/wp-content/uploads/2022/03/microsoft-s.png", "https://www.dexi.io/wp-content/uploads/2022/04/Layer-2-2-cropped.svg", "https://www.dexi.io/wp-content/uploads/2020/08/why-icon3.png", "https://www.dexi.io/wp-content/uploads/2020/09/longship-copy-1.png", "https://www.dexi.io/wp-content/uploads/2022/04/Layer-2-9.svg", "https://www.dexi.io/wp-content/uploads/2023/04/dexi_appsIcon_1.svg", "https://www.dexi.io/wp-content/uploads/2020/08/power-bi_logo-1.png", "https://www.dexi.io/wp-content/uploads/2022/06/Group-343-1-cropped.svg", "https://www.dexi.io/wp-content/uploads/2020/04/phone-thin-1.svg", "https://www.dexi.io/wp-content/uploads/2022/04/Layer-2-6.svg", "https://www.dexi.io/wp-content/uploads/2020/04/image-3-1-e1587638048477.png", "https://dexi.io/wp-content/uploads/2020/04/Layer-2-5.png", "https://www.dexi.io/wp-content/uploads/2020/04/software_etl.png", "https://www.dexi.io/wp-content/uploads/2020/12/Web-2.jpg", "https://www.dexi.io/wp-content/uploads/2020/08/rings_A.svg", "https://www.dexi.io/wp-content/uploads/2022/03/bose-s.png", "https://www.dexi.io/wp-content/uploads/2020/09/skanderbeg-1.png", "https://www.dexi.io/wp-content/uploads/2022/04/Layer-4-1.svg", "https://dexi.io/wp-content/uploads/2020/04/Design.png", "https://www.dexi.io/wp-content/uploads/2022/01/Rectangle-125-1.png", "https://www.dexi.io/wp-content/uploads/2020/08/shopping-cart-B-s-1.png", "https://www.dexi.io/wp-content/uploads/2020/08/dexibot-A-2.svg", "https://www.dexi.io/wp-content/uploads/2022/04/Layer-2.svg", "https://www.dexi.io/wp-content/uploads/2021/09/Testimonial-1_dexi-1.png", "https://www.dexi.io/wp-content/uploads/2022/04/Layer-4.svg", "https://www.dexi.io/wp-content/uploads/2020/08/pricing-B-s.png", "https://dexi.io/wp-content/uploads/2020/04/Group-345.png", "https://www.dexi.io/wp-content/uploads/2022/04/Page-1.svg", "https://dexi.io/wp-content/uploads/2020/04/Layer-2-2-1.png", "https://www.dexi.io/wp-content/uploads/2020/04/Layer-2-4-cropped.svg", "https://dexi.io/wp-content/uploads/2020/04/Layer-3-black.svg", "https://www.dexi.io/wp-content/uploads/2020/04/Services-icon2-1-1.svg", "https://www.dexi.io/wp-content/uploads/2020/12/2-1.jpg", "https://www.dexi.io/wp-content/uploads/2020/04/location-dot-thin-1.svg", "https://www.dexi.io/wp-content/uploads/2020/04/Group.svg", "https://www.dexi.io/wp-content/uploads/2020/04/services_solution.png", "https://www.dexi.io/wp-content/uploads/2020/04/envelope-thin-1.svg", "https://www.dexi.io/wp-content/uploads/2020/04/services_brief-1.png", "https://www.dexi.io/wp-content/uploads/2020/04/Group-343.svg", "https://www.dexi.io/wp-content/uploads/2022/03/loreal-s.png", "https://www.dexi.io/wp-content/uploads/2021/09/Testimonial-3_dexi-1.png", "https://www.dexi.io/wp-content/uploads/2020/08/graph-A-s.png", "https://www.dexi.io/wp-content/uploads/2020/04/Services-icon1-1.svg", "https://www.dexi.io/wp-content/uploads/2020/04/services_Implementation-1.png", "https://www.dexi.io/wp-content/uploads/2022/04/Layer-2-1-1.svg", "https://www.dexi.io/wp-content/uploads/2022/04/Layer-2-4.svg", "https://dexi.io/wp-content/uploads/2020/04/Layer-2-3.png", "https://www.dexi.io/wp-content/uploads/2022/03/hp-s.png", "https://www.dexi.io/wp-content/uploads/2020/08/extract-B-s.png", "https://www.dexi.io/wp-content/uploads/2020/04/services_poc.png", "https://www.dexi.io/wp-content/uploads/2020/08/power-bi_logo-5.png", "https://www.dexi.io/wp-content/uploads/2020/08/magnify-B-s.png", "https://www.dexi.io/wp-content/uploads/2021/09/Testimonial-2_dexi-1.png"));

		MainUrlServiceImpl mainUrlService = new MainUrlServiceImpl();
		MainUrlHandler obj = new MainUrlHandler(mainUrlService);
		List<String> actual = obj.getUrl("https://www.dexi.io/");

		for(int i=0;i<actual.size();i++)
		{
			if(expected.contains(actual.get(i))==false)
			{
				Assert.fail("This particular element was not present in the Expected set "+actual.get(i));
			}
		}

	}
	
}



