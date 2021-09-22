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
        const tickets = [{
            "movie": {
                "id": {
                    "id": "6551c5ef-192a-43db-93d1-d045b016631e"
                },
                "name": "movie1",
                "movieLength": {
                    "length": 10.0,
                    "unitOfTime": "min"
                },
                "genre": "action",
                "publishDate": "2021-09-22T10:07:11.243557Z",
                "description": "desc",
                "ticketPrice": {
                    "currency": "MKD",
                    "amount": 4.0
                },
                "scheduledMovies": []
            },
            "qty": 10
        }];

        props.onTicketReservationAdd(currency,tickets);
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
