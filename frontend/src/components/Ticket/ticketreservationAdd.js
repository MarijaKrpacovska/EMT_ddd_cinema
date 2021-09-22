import React from 'react';
import {useHistory} from 'react-router-dom';

const TicketReservationAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        currency: "",
        tickets: []
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
        const tickets = [];

        props.onTicketReservationAdd(currency,tickets);
        history.push("/movie");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="currency">currency</label>
                        <input type="text"
                               className="form-control"
                               id="currency"
                               name="currency"
                               required
                               placeholder="currency"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="tickets">tickets</label>
                        <input type="text"
                               className="form-control"
                               id="tickets"
                               name="tickets"
                               placeholder="tickets"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default TicketReservationAdd;
