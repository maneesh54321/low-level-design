### Use case
Backup files and folders
#### Actor
System
#### Steps
1. System reads location of folders to be backed up from locations.data.
2. System reads backup target folder location from application.properties.
3. System validates if folder denoted by the location to be backed up exists.
4. System copies the folder along with its contents to target location.
5. Repeat step 3-4 for each location in locations.data.

#### Nouns
System, location of folder to be backed up, locations.data, backup target folder location, application.properties

#### Verbs
reads, reads, validates, copies
