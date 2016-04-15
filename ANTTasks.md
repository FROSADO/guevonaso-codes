# Ant Task #

Some ant tasks.
## Size Task ##

> Set a property with the size (in KB) of a file or dir

## LastFile Task ##
> Set a property with the last modified file available in a folder.

> Sample use :
```
 <lastFile property="my.property">
   <fileset dir="C:/FOLDER">
     <include name="**/*.exe" />
     <exclude name="*Setup*" />
   </fileset>
</lastFile>
 
```

