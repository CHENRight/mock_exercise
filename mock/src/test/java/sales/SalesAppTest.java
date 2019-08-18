package sales;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class SalesAppTest {

	@Test
	public void testGenerateReport() {
		SalesApp salesApp = new SalesApp();
		salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
	}
	private boolean isSales(Sales sales){
		Date today = new Date();
		return sales == null || today.after(sales.getEffectiveTo()) || today.before(sales.getEffectiveFrom());
	}

	@Test
	public void should_return_sales_when_given_valid_date(){
		SalesApp spySalesApp = spy(new SalesApp());
		Date today = new Date();
		Date yesterday = new Date(today.getTime()-86400000L);
		Date tomorrow = new Date(today.getTime()+86400000L);

		Sales mockSales= mock(Sales.class);
		when(mockSales.getEffectiveFrom()).thenReturn(yesterday);
		when(mockSales.getEffectiveTo()).thenReturn(tomorrow);
		SalesDao salesDao=mock(SalesDao.class);
		when(salesDao.getSalesBySalesId(any())).thenReturn(mockSales);
		doReturn(salesDao).when(spySalesApp).getSalesDao();

		Sales sales = spySalesApp.getSales(any());
		assertNotNull(sales);
	}

	@Test
	public void should_return_null_when_given_wrong_date(){
		SalesApp spySalesApp = spy(new SalesApp());
		Date today = new Date();
		Date yesterday = new Date(today.getTime()-86400000L);
		Date tomorrow = new Date(today.getTime()-86400000L);

		Sales mockSales= mock(Sales.class);
		when(mockSales.getEffectiveFrom()).thenReturn(yesterday);
		when(mockSales.getEffectiveTo()).thenReturn(tomorrow);
		SalesDao salesDao=mock(SalesDao.class);
		when(salesDao.getSalesBySalesId(any())).thenReturn(mockSales);
		doReturn(salesDao).when(spySalesApp).getSalesDao();

		Sales sales = spySalesApp.getSales(any());
		assertNull(sales);
	}
	@Test
	public void should_return_list_contains_Time_when_isNatTrade_is_true(){
		SalesApp salesApp = new SalesApp();

		List<String> list=salesApp.getHeaders(true);

		assertTrue(list.contains("Time"));
	}

	@Test
	public void should_return_list_contains_Local_Time_when_isNatTrade_is_false(){
		SalesApp salesApp = new SalesApp();

		List<String> list=salesApp.getHeaders(false);

		assertTrue(list.contains("Local Time"));
	}

	@Test
	public void should_uploadDocument_when_report_is_not_null(){
		SalesApp spySalesApp = spy(new SalesApp());
		SalesReportDao salesReportDao = mock(SalesReportDao.class);

		when(salesReportDao.getReportData(any())).thenReturn(new ArrayList<>());
		doReturn(new Sales()).when(spySalesApp).getSales(any());
		doReturn(salesReportDao).when(spySalesApp).getSalesReportDao();
		doReturn(new ArrayList<>()).when(spySalesApp).getHeaders(anyBoolean());
		doReturn(new SalesActivityReport()).when(spySalesApp).generateReport(any(),any());

		spySalesApp.generateSalesActivityReport(anyString(),anyInt(),anyBoolean(),anyBoolean());

		verify(spySalesApp,times(1)).uploadDocument(any());
	}
}
