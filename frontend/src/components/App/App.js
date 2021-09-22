import './App.css';
import React, {Component} from "react";
import Movies from '../Movie/movie';
import MovieDetails from '../Movie/movieDetails'
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import MovieService from "../../repository/movieRepository";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies: []
        }
    }

    render() {
        return (
                <Router>
                    <div className="container">
                        <Route path={"/movie/details/:id"} exact render={() =>
                            <MovieDetails selectedMovie={this.state.movies}/>}/>
                        <Route path={"/movie"} exact render={() =>
                            <Movies movies={this.state.movies}
                                    onDetails={this.getMovie}/>}/>
                    </div>
                </Router>
        );
    }

    componentDidMount() {
        this.loadMovies();
    }

    loadMovies = () => {
        MovieService.fetchMovies()
            .then((data) => {
                this.setState({
                    movies: data.data
                })
            });
    }
    getMovie = (id) => {
        MovieService.getMovie(id)
            .then((data) => {
                this.setState({
                    selectedMovie: data.data
                })
            })
    }

}

export default App;
