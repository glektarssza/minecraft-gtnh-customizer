const fs = require('node:fs/promises');
const path = require('node:path');

/**
 * Upload all files in a directory to a release.
 *
 * @param {object} param0 The GitHub context object.
 * @param {string} artifactDirectory The path to the directory containing the
 * artifacts to upload.
 * @param {string} releaseId The ID of the release to upload artifacts to.
 *
 * @returns {Promise<void>} A promise that resolves when all assets have been
 * uploaded.
 */
module.exports = async (
    {github, context, core},
    artifactDirectory,
    releaseId
) => {
    const {owner, repo} = context.repo;
    const allDirItems = await fs.readdir(artifactDirectory, {
        withFileTypes: true,
        encoding: 'utf-8',
        recursive: true
    });
    const assets = allDirItems.filter((item) => item.isFile());
    core.info(`Uploading ${assets.length} assets to release ${releaseId}...`);
    for (const asset of assets) {
        const artifactFullPath = path.join(asset.parentPath, asset.name);
        const artifactName = asset.name;
        core.info(`Uploading ${artifactFullPath}...`);
        core.info(`Reading ${artifactName} from ${artifactFullPath}...`);
        const data = await fs.readFile(artifactFullPath);
        core.info(`Uploading to release as ${artifactName}...`);
        await github.rest.repos.uploadReleaseAsset({
            owner,
            repo,
            release_id: releaseId,
            name: artifactName,
            data
        });
    }
    core.info('All release assets uploaded!');
};
