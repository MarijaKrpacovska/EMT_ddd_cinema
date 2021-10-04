import axios from '../custom-axios/axios_scheduled_movie';

const ScheduledMovieService = {
    fetchScheduledMovies: () => {
        return axios.get("/scheduledMovie");
    }

}

export default ScheduledMovieService;