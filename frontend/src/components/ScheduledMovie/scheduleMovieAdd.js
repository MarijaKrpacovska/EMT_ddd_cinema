import React from 'react';
import {useHistory} from 'react-router-dom';

const ScheduledMovieAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        sales : 0,
        startTime : {
            startingHour: 0,
            startingMinutes : 0
        },
        endTime : {
            endingHour: 0,
            endingMinutes: 0
        },
        ticketPrice : {
            ticketPriceCurrency: "MKD",
            ticketPriceAmount: 0.0
        },
        movieId : "9a78fd3e-9caf-490a-a1d4-c91852494c05"
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const sales = 4;
        const startTime = {
            "hour": formData.startingHour,
            "minutes": formData.startingMinutes
        };
        const endTime = {
            "hour": formData.endingHour,
            "minutes": formData.endingMinutes
        };
        const ticketPrice = {
            "currency": "MKD",
            "amount": formData.ticketPriceAmount
        };
        const movieId= props.selectedMovie.id.id;

        props.onAddScheduledMovie(sales, startTime, endTime, ticketPrice, movieId);
        history.push("/scheduledMovies");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Start hour</label>
                        <input type="number"
                               className="form-control"
                               id="startingHour"
                               name="startingHour"
                               required
                               placeholder="Enter Movie startingHour"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Start minutes</label>
                        <input type="number"
                               className="form-control"
                               id="startingMinutes"
                               name="startingMinutes"
                               required
                               placeholder="Enter Movie startingMinutes"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Ending hour</label>
                        <input type="number"
                               className="form-control"
                               id="endingHour"
                               name="endingHour"
                               required
                               placeholder="Enter Movie endingHour"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Ending minutes</label>
                        <input type="number"
                               className="form-control"
                               id="endingMinutes"
                               name="endingMinutes"
                               required
                               placeholder="Enter Movie endingMinutes"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">TicketPriceCurrency</label>
                        <input type="text"
                               className="form-control"
                               id="ticketPriceCurrency"
                               name="ticketPriceCurrency"
                               required
                               placeholder="Enter Movie ticketPriceCurrency"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name"> ticketPriceAmount</label>
                        <input type="number"
                               className="form-control"
                               id="ticketPriceAmount"
                               name="ticketPriceAmount"
                               required
                               placeholder="Enter Movie ticketPriceAmount"
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-dark">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default ScheduledMovieAdd;
