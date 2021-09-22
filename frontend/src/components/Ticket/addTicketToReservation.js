import React from 'react';
import {useHistory} from 'react-router-dom';

const AddTicketToReservation = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        movie: {},
        quantity: 1
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const quantity = formData.quantity;
        const movie = {
            "id": {
                "id": "7f4d8d50-11bc-4ce5-984b-48551bfeb89a"
            },
            "name": "movie",
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
        }

        props.onAddTicketToReservation(props.ticketReservation.id, quantity,movie);
        history.push("/movie");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="quantity">quantity</label>
                        <input type="text"
                               className="form-control"
                               id="quantity"
                               name="quantity"
                               placeholder="quantity"
                               onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="movie">movie</label>
                        <input type="text"
                               className="form-control"
                               id="movie"
                               name="movie"
                               placeholder="movie"
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default AddTicketToReservation;
