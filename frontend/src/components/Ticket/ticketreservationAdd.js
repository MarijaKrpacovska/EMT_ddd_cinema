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
                scheduledMovie: {
                    id: {
                        id: "4d219a3f-12de-4630-8fa5-86f23804627b"
                    },
                    sales: 10,
                    startTime: {
                        hour: 10,
                        minutes: 10
                    },
                    endTime: {
                        hour: 10,
                        minutes: 10
                    },
                    ticketPrice: {
                        currency: "MKD",
                        amount: 30.0
                    },
                    movieId: {
                        id: "9a78fd3e-9caf-490a-a1d4-c91852494c05"
                    }
                }
            }
        ];

        const reservationTime = formData.reservationTime;
        const reservationStatus = "ACTIVE";
        const paymentMethod = "CASH"
        props.onTicketReservationAdd(reservationTime,currency,reservationStatus,paymentMethod,tickets);
        history.push("/movie");
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
                        <label htmlFor="reservationTime">reservationTime</label>
                        <input type="text"
                               className="form-control"
                               id="reservationTime"
                               name="reservationTime"
                               placeholder="reservationTime"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="movie" className="form-control" onChange={handleChange}>
                            {props.movies.map((term) =>
                                <option value={term.id}>{term.name}</option>
                            )}
                        </select>
                    </div>

                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default TicketReservationAdd;
