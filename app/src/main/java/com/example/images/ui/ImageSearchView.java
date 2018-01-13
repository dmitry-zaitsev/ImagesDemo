package com.example.images.ui;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Allows user to search for the images and shows the result.
 */
public interface ImageSearchView {

    /**
     * Assigns listener to the view.
     */
    void setListener(@NonNull Listener listener);

    /**
     * Updates state of the view. By default view is considered to be in {@link State.Default}.
     */
    void updateState(@NonNull State state);

    /**
     * Notified about user's interaction with the view.
     */
    interface Listener {

        /**
         * Called when user would like to see more results for the same query.
         */
        void requestMoreResults();

        /**
         * Called when user changes the search query.
         */
        void onQueryUpdated(@NonNull String query);

        /**
         * Null object which does nothing.
         */
        Listener NULL = new Listener() {
            @Override
            public void requestMoreResults() {
                // Do nothing
            }

            @Override
            public void onQueryUpdated(@NonNull String query) {
                // Do nothing
            }
        };

    }

    /**
     * Item which could be presented in the results.
     * <p>
     * Represented as an algebraic data type.
     */
    class Item {

        /**
         * Image which is stored on the remote server.
         */
        public static class Image extends Item {

            @NonNull
            public final String url;

            public Image(@NonNull String url) {
                this.url = url;
            }

        }

        /**
         * Indicates that more results are being loaded at the moment.
         */
        public static class Loading extends Item {
        }

    }

    /**
     * State in which view is currently in.
     * <p>
     * Represented as an algebraic data type.
     */
    class State {

        /**
         * User did not attempt to search for anything.
         */
        public static class Default extends State {
        }

        /**
         * Something is being loaded.
         */
        public static class Loading extends State {
        }

        /**
         * User tried to search for something but nothing was found.
         */
        public static class NoResults extends State {
        }

        /**
         * User tried to search for something and something was found.
         */
        public static class LoadedResults extends State {

            @NonNull
            public final List<Item> items;

            public LoadedResults(@NonNull List<Item> items) {
                this.items = items;
            }

        }

    }

}
