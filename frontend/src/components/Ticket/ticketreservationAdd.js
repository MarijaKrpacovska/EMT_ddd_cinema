import React from 'react';
import {useHistory} from 'react-router-dom';

const TicketReservationAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        currency: "",
        tickets: [],
        reservationTime : "2021-10-04T12:56:06.188568Z",
        reservationStatus : "ACTIVE",
        paymentMethod : "CASH"
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const currency = "MKD";
        const tickets = [
            {
                quantity: 3,
                scheduledMovie: props.scheduledMovie

            // {
            //     id: {
            //         id: "4d219a3f-12de-4630-8fa5-86f23804627b"
            //     },
            //     sales: 10,
            //     startTime: {
            //         hour: 10,
            //         minutes: 10
            //     },
            //     endTime: {
            //         hour: 10,
            //         minutes: 10
            //     },
            //     ticketPrice: {
            //         currency: "MKD",
            //         amount: 30.0
            //     },
            //     movieId: {
            //         id: "9a78fd3e-9caf-490a-a1d4-c91852494c05"
            //     }
            // }
            }
        ];

        const reservationTime = "2021-10-04T12:56:06.188568Z";
        const reservationStatus = "ACTIVE";
        const paymentMethod = "CASH"
        props.onTicketReservationAdd(reservationTime,currency,reservationStatus,paymentMethod,tickets);
        history.push("/ticket/activeReservation");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label>currency</label>
                        <select name="currency" className="form-control" onChange={handleChange}>
                            <option value="MKD">MKD</option>
                            <option value="USD">USD</option>
                            <option value="EUR">EUR</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label>currency</label>
                        <select name="paymentMethod" className="form-control" onChange={handleChange}>
                            <option value="CASH">CASH</option>
                            <option value="CREDIT_CARD">CREDIT CARD</option>
                        </select>
                    </div>

                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
            <div className={"col-md-4 imgDiv"} hidden={"true"}>
                <img id={"imgImg"} className={"imgImg"} height={"1130px"} src={"https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg"}/>
            </div>
        </div>
    )
}

export default TicketReservationAdd;
