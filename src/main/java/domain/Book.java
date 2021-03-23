package domain;

public interface Book {
    String getTitle();

    int getId();

    class PhilosophalStone implements Book {

        @Override
        public String getTitle() {
            return "Harry Potter and the Philosophal Stone";
        }

        @Override
        public int getId() {
            return 1;
        }
    }

    class ChamberOfSecrets implements Book {

        @Override
        public String getTitle() {
            return "Harry Potter and the Chamber Of Secrets";
        }

        @Override
        public int getId() {
            return 2;
        }
    }

    class PrisonerAzkaban implements Book {

        @Override
        public String getTitle() {
            return "Harry Potter and the Prisoner of Azkaban";
        }

        @Override
        public int getId() {
            return 3;
        }
    }

    class GobletOfFire implements Book {

        @Override
        public String getTitle() {
            return "Harry Potter and the Goblet of Fire";
        }

        @Override
        public int getId() {
            return 4;
        }
    }

    class OrderOfPhoenix implements Book {

        @Override
        public String getTitle() {
            return "Harry Potter and the Order of the Phoenix";
        }

        @Override
        public int getId() {
            return 5;
        }
    }
}
