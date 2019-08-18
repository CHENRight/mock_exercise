package parking;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */

        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);

        when(parkingLot.getName()).thenReturn("ParkingLot");

        when(car.getName()).thenReturn("CarName");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

        Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);

        assertEquals("ParkingLot", receipt.getParkingLotName());
        assertEquals("CarName", receipt.getCarName());

    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        Car leoCar = new Car("leocar");

        mock(InOrderParkingStrategy.class);
        InOrderParkingStrategy spyInOrderParkingStrategy = spy(InOrderParkingStrategy.class);

        String carName = "CarName";
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);

        assertEquals(carName, receipt.getCarName());
        assertEquals(NO_PARKING_LOT, receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        Car leoCar = new Car("leocar");
        ParkingLot parkingLot = new ParkingLot("parkinglot",1);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);

        InOrderParkingStrategy spyInOrderParkingStrategy = spy(new InOrderParkingStrategy());

        Receipt receipt = spyInOrderParkingStrategy.park(parkingLots,leoCar);

        verify(spyInOrderParkingStrategy).createReceipt(parkingLot,leoCar);

        assertEquals("leocar",receipt.getCarName());
        assertEquals("parkinglot",receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){
        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        Car leoCar = new Car("leocar");
        ParkingLot parkingLot1 = new ParkingLot("parkinglot1",1);
        parkingLot1.getParkedCars().add(new Car("car1"));

        ParkingLot parkingLot2 = new ParkingLot("parkinglot2",1);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1,parkingLot2);

        InOrderParkingStrategy spyInOrderParkingStrategy = spy(new InOrderParkingStrategy());

        Receipt receipt = spyInOrderParkingStrategy.park(parkingLots,leoCar);

        verify(spyInOrderParkingStrategy).createReceipt(parkingLot2,leoCar);
        assertEquals("leocar",receipt.getCarName());
        assertEquals("parkinglot2",receipt.getParkingLotName());
    }


}
