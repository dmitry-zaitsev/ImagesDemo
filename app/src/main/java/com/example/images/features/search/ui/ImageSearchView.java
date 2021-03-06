package com.example.images.features.search.ui;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Allows user to search for the images and shows the value.
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
        public static final class Image extends Item {

            @NonNull
            public final String url;

            public Image(@NonNull String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "Image{" +
                        "url='" + url + '\'' +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Image image = (Image) o;

                return url.equals(image.url);
            }

            @Override
            public int hashCode() {
                return url.hashCode();
            }

        }

        /**
         * Indicates that more results are being loaded at the moment.
         */
        public static final class Loading extends Item {

            public static final Loading INSTANCE = new Loading();

            private Loading() {
            }

            @Override
            public String toString() {
                return "Loading";
            }

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
        public static final class Default extends State {

            public static final Default INSTANCE = new Default();

            private Default() {
            }

            @Override
            public String toString() {
                return "Default";
            }
        }

        /**
         * Something is being loaded.
         */
        public static final class Loading extends State {

            public static final Loading INSTANCE = new Loading();

            private Loading() {
            }

            @Override
            public String toString() {
                return "Loading";
            }
        }

        /**
         * User tried to search for something but nothing was found.
         */
        public static final class NoResults extends State {

            public static final NoResults INSTANCE = new NoResults();

            private NoResults() {
            }

            @Override
            public String toString() {
                return "NoResults";
            }
        }

        /**
         * Something went wrong and results can't be loaded.
         */
        public static final class Failure extends State {

            public static final Failure INSTANCE = new Failure();

            private Failure() {
            }

            @Override
            public String toString() {
                return "Failure";
            }

        }

        /**
         * User tried to search for something and something was found.
         */
        public static final class LoadedResults extends State {

            @NonNull
            public final List<Item> items;
            public final boolean morePagesAvailable;

            public LoadedResults(@NonNull List<Item> items, boolean morePagesAvailable) {
                this.items = items;
                this.morePagesAvailable = morePagesAvailable;
            }

            @Override
            public String toString() {
                return "LoadedResults{" +
                        "items=" + items +
                        ", morePagesAvailable=" + morePagesAvailable +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                LoadedResults that = (LoadedResults) o;

                return morePagesAvailable == that.morePagesAvailable
                        && items.equals(that.items);
            }

            @Override
            public int hashCode() {
                int result = items.hashCode();
                result = 31 * result + (morePagesAvailable ? 1 : 0);
                return result;
            }

        }

    }

}
