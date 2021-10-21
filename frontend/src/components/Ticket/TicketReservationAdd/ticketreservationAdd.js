import React from 'react';
import {useHistory} from 'react-router-dom';

const TicketReservationAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        currency: "MKD",
        ticketsQuantity: 1,
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
        const currency = formData.currency;
        const tickets = [
            {
                qty: formData.ticketsQuantity,
                scheduledMovie: props.scheduledMovie
            }
        ];
        const paymentMethod = formData.paymentMethod;
        props.onTicketReservationAdd(currency,paymentMethod,tickets);
        history.push(`/ticket/getTicketReservation/id`);
    }

    return(
        <div className="row mt-5">
            <div className="col-md-6">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group mb-2">
                        <label htmlFor={"ticketsQuantity"}>Number of tickets: </label>
                        <input type="number"
                               defaultValue="1"
                               min="1"
                               className="form-control m-1"
                               id="ticketsQuantity"
                               name="ticketsQuantity"
                               required
                               placeholder="Enter price of tickets"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor={"paymentMethod"}>Payment method:</label>
                        <select name="paymentMethod" className="form-control" onChange={handleChange}>
                            <option value="CASH">CASH</option>
                            <option value="CREDIT_CARD">CREDIT CARD</option>
                        </select>
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor={"currency"}>Payment Currency:</label>
                        <select name="currency" className="form-control" onChange={handleChange}>
                            <option value="MKD">MKD</option>
                            <option value="USD">USD</option>
                            <option value="EUR">EUR</option>
                        </select>
                    </div>


                    <div className={"text-center"}>
                    <button id="submit" type="submit" className="btn btn-dark">Submit</button>
                    </div>
                    </form>
            </div>
            <div className={"col-md-6 text-center"}>
                <img height={"200px"} src={"https://www.pinclipart.com/picdir/big/134-1340641_desenhos-de-pessoas-no-cinema-clipart.png"}/>
            </div>
        </div>
    )
}

export default TicketReservationAdd;
