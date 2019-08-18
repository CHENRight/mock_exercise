package sales;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {

	public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {
		Sales sales = getSales(salesId);
		if (sales == null) { return;}
		List<SalesReportData> reportDataList = getSalesReportDao().getReportData(sales);
		List<String> headers = getHeaders(isNatTrade);
		SalesActivityReport report = this.generateReport(headers, reportDataList);
		if (report == null) {
			return;
		}
		uploadDocument(report);
	}

	protected Sales getSales(String salesId) {
		Sales sales = getSalesDao().getSalesBySalesId(salesId);
		if (isSales(sales)) {
			return null;
		}
		return sales;
	}

	private boolean isSales(Sales sales){
		Date today = new Date();
		return sales == null || today.after(sales.getEffectiveTo()) || today.before(sales.getEffectiveFrom());
	}

	protected List<String> getHeaders(boolean isNatTrade) {
		if (isNatTrade) {
			return Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		}
		return Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
	}

	protected void uploadDocument(SalesActivityReport report) {
		getEcmService().uploadDocument(report.toXml());
	}

	protected SalesDao getSalesDao() {
		return new SalesDao();
	}
	protected SalesReportDao getSalesReportDao() {
		return new SalesReportDao();
	}
	protected EcmService getEcmService() {
		return new EcmService();
	}

	protected SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}
}
