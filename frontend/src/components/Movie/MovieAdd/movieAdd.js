import './movieAdd.css';
import React from 'react';
import {useHistory} from 'react-router-dom';

//todo: fix forms
//todo: fix warnings in console
const MovieAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        movieLength: {
            length: 0.0,
            unitOfTime: "min"
        },
        genre: "action",
        publishDate: "2021-01-01",
        description: "desc",
        ticketPrice: {
            currency: "MKD",
            amount: 0.0
        },
        moviePoster : {
            imageUrl: "",
            imageType: "poster"
        },
        movieAdvertisementImage : {
            imageUrl: "",
            imageType: "advertisement"
        },
        trailerUrl: "",
        scheduledMovies: []
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const publishDate = formData.publishDate;
        const description = formData.description;
        const movieLength = {
                length: formData.movieLength,
                unitOfTime: "min"
            };
        const genre= formData.genre;

        const ticketPrice= {
            currency: "MKD",
                amount: formData.ticketPrice
        };
        const moviePoster = {
            imageUrl: formData.moviePoster,
                imageType: "poster"
        };
        const movieAdvertisementImage = {
            imageUrl: formData.movieAdvertisementImage,
            imageType: "advertisement"
        };
        const trailerUrl = formData.trailerUrl

        props.onAddMovie(name,movieLength,genre, publishDate,description,ticketPrice,moviePoster,movieAdvertisementImage,trailerUrl);
        history.push("/movie");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter movie name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="publishDate">Publish date</label>
                        <input type="date"
                               className="form-control"
                               id="publishDate"
                               name="publishDate"
                               required
                               placeholder="Enter Movie publishDate"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Description</label>
                        <input type="text"
                               className="form-control"
                               id="description"
                               name="description"
                               placeholder="Enter short movie description"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Genre</label>
                        <select name="genre" className="form-control" onChange={handleChange}>
                            <option value="action">action</option>
                            <option value="romance">romance</option>
                            <option value="horror">horror</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label htmlFor="movieLength">Length</label>
                        <input type="number"
                               className="form-control"
                               id="movieLength"
                               name="movieLength"
                               placeholder="[min]"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="ticketPrice">Ticket Price</label>
                        <input type="number"
                               className="form-control"
                               id="ticketPrice"
                               name="ticketPrice"
                               placeholder="Enter ticket price"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="moviePoster">Movie poster url</label>
                        <input type="text"
                               className="form-control"
                               id="moviePoster"
                               name="moviePoster"
                               required
                               placeholder="Enter poster image url"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="movieAdvertisementImage">Movie poster url</label>
                        <input type="text"
                               className="form-control"
                               id="movieAdvertisementImage"
                               name="movieAdvertisementImage"
                               required
                               placeholder="Enter poster image url"
                               onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="trailerUrl">Movie trailer video url </label> <a className="tooltipp" title="This is some information for our tooltip." href="#">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             className="bi bi-question-circle" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                            <path
                                d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
                        </svg>
                    </a>
                        <input type="text"
                               className="form-control"
                               id="trailerUrl"
                               name="trailerUrl"
                               required
                               placeholder="Enter trailer video url"
                               onChange={handleChange}
                        />
                    </div>


                    <button id="submit" type="submit" className="btn btn-dark align-self-md-center">Submit</button>

                    </form>
            </div>

        </div>
    )
}

export default MovieAdd;
